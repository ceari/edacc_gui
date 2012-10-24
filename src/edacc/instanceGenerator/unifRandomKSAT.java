/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edacc.instanceGenerator;

import java.security.SecureRandom;
import java.util.*;

/**
 *
 * @author balint
 */
public class unifRandomKSAT {

    private int numVariables;
    private int numClauses;
    private int clauseLength;
    private double ratio;
    private long seed;
    private HashMap<ArrayList<Integer>, HashSet<Integer>> formula;
    private String parameterLine;
    public String comment;
    private Random random;

    /**
     *
     * @param k
     *            K-SAT
     * @param nV
     *            number of variables
     * @param r
     *            ratio between number of variables and number of clauses;
     *            ratio=#clauses/#vars
     */
    public unifRandomKSAT(int k, double r,int nV ) {//throws Exception {
       /* if (nV <= 0) {
            throw new Exception("number of variables should be a positiv number");
        }
        if (r <= 0) {
            throw new Exception("ratio should be a positiv number");
        }
        if (k <= 0) {
            throw new Exception("length of the clause should be a positiv number");
        }*/
        this.numVariables = nV;
        this.numClauses = (int) (nV * r);
        this.clauseLength = k;
        this.ratio = r;
        //initialize the random number generator with a seed generated by the SecureRandom.generateSeed
        this.seed = this.generateSeed();
        random = new Random(this.seed);

        this.comment = "c Uniform Random k-SAT Generator from EDACC \n";
        this.comment += "c uniform random k-SAT generated instance with: \n";
        this.comment += "c seed: " + this.seed + " \n";
        this.comment += "c number variables: " + this.numVariables + " \n";
        this.comment += "c number clauses: " + this.numClauses + " \n";
        this.comment += "c ratio: " + this.ratio + " \n";
        this.parameterLine = "p cnf " + numVariables + " " + numClauses + "\n";		//construct the parameter line

        this.formula = new HashMap<ArrayList<Integer>, HashSet<Integer>>(3 * this.numClauses, 0.5f);
        HashSet<Integer> clause;
        ArrayList<Integer> sortedClause;

        while (formula.size() < this.numClauses) {
            clause = this.generateClause(clauseLength);
            sortedClause = new ArrayList<Integer>(clause);
            Collections.sort(sortedClause);
            formula.put(sortedClause, clause);
        }
    }

    /**
     * Generates a seed with the help of the SecureRandom seed generator
     *
     * @return a long seed
     */
    private long generateSeed() {
        byte[] secureSeed;
        secureSeed = SecureRandom.getSeed(8);
        long tempSeed = 0;
        for (int i = 0; i < 8; i++) {
            tempSeed <<= 8;
            tempSeed ^= secureSeed[i] & 0xFF;
        }
        tempSeed = Math.abs(tempSeed);
        return tempSeed;
    }

    /**
     * Creates a clause with length unique literals
     *
     * @param length
     * @return a Vector containing the literals
     */
    private HashSet<Integer> generateClause(int length) {
        HashSet<Integer> clause = new HashSet<Integer>(3 * length, 0.5f);
        int lit;
        while (clause.size() < length) {
            lit = random.nextInt(numVariables) + 1;
            if (random.nextBoolean()) // negate or not!
            {
                lit = -lit;
            }
            if (!clause.contains(-lit)) {
                clause.add(lit);
            }
        }
        return clause;
    }

    public String formulaStatistics() {
        String statistics = "";
        HashSet<Integer> clause;
        int[] numOcc = new int[2 * this.numVariables + 1];
        for (int i = 0; i < this.numVariables * 2 + 1; i++) {
            numOcc[i] = 0;
        }

        double medianNumOcc = 0;
        int numPureLit = 0;
        for (Iterator<HashSet<Integer>> itrC = formula.values().iterator(); itrC.hasNext();) {
            clause = (HashSet<Integer>) itrC.next();
            for (Iterator<Integer> itrL = clause.iterator(); itrL.hasNext();) {
                numOcc[this.numVariables + itrL.next()]++;
            }
        }
        int min = this.numVariables, max = -this.numVariables;
        int sum = 0;
        for (int i = 0; i < this.numVariables * 2 + 1; i++) {
            if (numOcc[i] > max) {
                max = numOcc[i];
            }
            if ((numOcc[i] < min) && (i != this.numVariables)) {
                min = numOcc[i];
            }
            if (numOcc[i] == 0) {
                numPureLit++;
            }
            sum += numOcc[i];
        }
        medianNumOcc = (double) sum / (double) 2 * this.numVariables;
        //Formatter formatter=new Formatter();

        statistics += "c Maximum number of occurrences of a literal= " + max + "\n";
        statistics += "c Minimum number of occurrences of a literal= " + min + "\n";
        statistics += "c Number of occurrences of pure literals= " + numPureLit + "\n";
        //statistics+="c Average number of occurrences of a literal= "+  formatter.format("%2.4f", medianNumOcc) + "\n";
        return statistics;
    }

    /**
     * Generates the String representation of the formula in conformity to the
     * cnf format
     *
     * @return a String representing the formula
     */
    public String toDIMACS() {
     int literalLength=String.valueOf(this.numVariables).length()+2;//literal+sign+space
        int dimacsLength=this.comment.length()+this.parameterLine.length()+
                this.numClauses*(this.clauseLength*literalLength+2);
        StringBuilder output=new StringBuilder();
        int i = 1;
        output.append(comment);
        output.append(parameterLine);
        HashSet<Integer> clause;
        // Vector<HashSet<Integer>> f
        // =(Vector<HashSet<Integer>>)formula.values();
        for (Iterator<HashSet<Integer>> itrC = formula.values().iterator(); itrC.hasNext(); i++) {
            // output+=i+" : ";
            clause = (HashSet<Integer>) itrC.next();
            for (Iterator<Integer> itrL = clause.iterator(); itrL.hasNext();) {
                output.append(itrL.next());
                if (itrL.hasNext()) {
                    output.append(" ");
                } else {
                    output.append(" 0\n");
                }
            }
        }
        return output.toString();
    }

     /**
     *
     * @return a String with the suggested file name for this formula as used in
     *         the SAT Competiton
     */
    public String suggestedFN() {
        return "unif-k" + this.clauseLength + "-r" + this.ratio + "-v"
                + this.numVariables + "-c" + this.numClauses + "-S" + this.seed
                + ".cnf";
    }

    /**
     *
     * @return a String with the suggested class name for this formula
     */
    public String suggestedClassName() {
        return "UnifRandom-k" + this.clauseLength + "-r" + this.ratio;
    }

}

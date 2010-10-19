/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edacc.properties;

import edacc.model.ExperimentResultDAO;
import edacc.model.ExperimentResultHasProperty;
import edacc.model.ExperimentResultHasPropertyDAO;
import edacc.model.InstanceDAO;
import edacc.model.InstanceHasProperty;
import edacc.model.NoConnectionToDBException;
import edacc.model.Property;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rretz
 */
public class PropertyComputationUnit implements Runnable {
    ExperimentResultHasProperty erhp;
    InstanceHasProperty ihp;
    PropertyComputationController callback;
    Property property;

    PropertyComputationUnit(ExperimentResultHasProperty erhp, PropertyComputationController callback) {
        this.erhp = erhp;
        this.callback = callback;
        this.property = erhp.getProperty();
    }

    PropertyComputationUnit(InstanceHasProperty ihp, PropertyComputationController callback) {
        this.ihp = ihp;
        this.callback = callback;
        this.property = ihp.getProperty();
    }

    @Override
    public void run() {
        if(erhp != null){
            try {
                Property property = erhp.getProperty();
                switch (property.getPropertySource()) {
                    case LauncherOutput:
                        calculate(ExperimentResultDAO.getLauncherOutputFile(erhp.getExpResult()));
                        break;
                    case SolverOutput:
                        calculate(ExperimentResultDAO.getSolverOutputFile(erhp.getExpResult()));
                        break;
                    case VerifierOutput:
                        calculate(ExperimentResultDAO.getVerifierOutputFile(erhp.getExpResult()));
                        break;
                    case WatcherOutput:
                        calculate(ExperimentResultDAO.getWatcherOutputFile(erhp.getExpResult()));
                        break;
                }
            } catch (NoConnectionToDBException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ihp != null){
            try {
                switch (property.getPropertySource()) {
                    case Instance:
                        calculate(InstanceDAO.getBinaryFileOfInstance(ihp.getInstance()));
                        break;
                    case InstanceName:
                        parse(ihp.getInstance().getName());
                        break;
                }
            } catch (NoConnectionToDBException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PropertyComputationUnit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        callback.callback();  
    }

    private void calculate(File f) {
        if(property.getComputationMethod() != null){
            // TODO compute the paroperty with the computation binary on the given file
        }else if(!property.getRegularExpression().equals("") || property.getRegularExpression() != null){
            // TODO compute the paroperty with the regular expression on the given file
        }
    }

    private void parse(String toParse) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


}

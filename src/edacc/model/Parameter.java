package edacc.model;

public class Parameter extends BaseModel implements IntegerPKModel {
    private int id;
    private int idSolver;
    private String name;
    private String prefix;
    private String value;
    private int order;
    private boolean hasValue;
    private boolean mandatory;
    private boolean space;

    public Parameter() {
        super();
        name = prefix = value = "";
        hasValue=true;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (this.isSaved()) {
            this.setModified();
        }
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
        if (this.isSaved()) {
            this.setModified();
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        if (this.isSaved()) {
            this.setModified();
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        if (this.isSaved()) {
            this.setModified();
        }
    }

    public boolean getHasValue() {
        return hasValue;
    }

    public void setHasValue(boolean hasValue) {
        this.hasValue = hasValue;
        if (this.isSaved()) {
            this.setModified();
        }
    }

    /**
     * @return the mandatory
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * @param mandatory the mandatory to set
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
        if (this.isSaved()) {
            this.setModified();
        }
    }

    /**
     * @return the space
     */
    public boolean getSpace() {
        return space;
    }

    /**
     * @param space the space to set
     */
    public void setSpace(boolean space) {
        this.space = space;
        if (this.isSaved()) {
            this.setModified();
        }
    }

    public int getIdSolver() {
        return idSolver;
    }

    public void setIdSolver(int idSolver) {
        this.idSolver = idSolver;
    }
}

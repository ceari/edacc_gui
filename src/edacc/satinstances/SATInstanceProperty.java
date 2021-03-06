/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edacc.satinstances;

/**
 * An interface every instance propery must implement for computing the property
 * value.
 * @author dgall
 */
public interface SATInstanceProperty {

    /**
     *
     * @return the name of the property.
     */
    public String getName();

    /**
     *
     * @param f
     * @return the value of the property for a given instance.
     */
    public Object computeProperty(SATInstance f);
    //public Float computeProperty(SATInstance f);

    public PropertyValueType<?> getPropertyValueType();

}

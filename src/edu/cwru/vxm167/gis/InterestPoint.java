
package edu.cwru.vxm167.gis;

import java.util.*;
import java.math.*;


public record InterestPoint<M>(Coordinate coordinate, M marker){

public String toString(){
        return this.coordinate.toSimpleString()+" : "+this.marker.toString();
        }

public final InterestPoint validate(){
        if(this.marker == null) throw new NullPointerException("The marker cannot be null");
        else{
        this.coordinate.validate();
        return this;
        }
        }

public static final InterestPoint validate(InterestPoint interestpoint){
        if(interestpoint.coordinate() == null)throw new NullPointerException("The argument is null");
        else return interestpoint.validate();
        }

public boolean hasMarker(M marker){
        if (this.marker == marker) return true;
        else return false;
        }
        }

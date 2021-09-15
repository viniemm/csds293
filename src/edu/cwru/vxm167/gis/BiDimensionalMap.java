
package edu.cwru.vxm167.gis;

import java.util.*;
import java.math.*;
import java.util.function.Supplier;

public final class BiDimensionalMap<T>{

    private final SortedMap<BigDecimal, SortedMap<BigDecimal, Collection<T>>> points = new TreeMap<>();

    public final Collection<T> get(BigDecimal x, BigDecimal y){
        if(x==null||y==null){
            throw new NullPointerException("Argument cannot be null");
        }
        else{
            return this.ge;
        }
    }

    public final Collection<T> get(Coordinate coordinate){
        if(coordinate == null){
            throw new NullPointerException("Argument cannot be null");
        }
        else{
            return get(coordinate.x(),coordinate.y());
        }
    }

    public final class Updater{

        private BigDecimal x = new BigDecimal("0.0");
        private BigDecimal y = new BigDecimal("0.0");

        private Supplier<Collection<T>> collectionFactory = HashSet::new;

        private Collection<T> values = collectionFactory.get();

        public final Updater setX(BigDecimal new_x){
            this.x = new_x;
            return this;
        }

        public final Updater setY(BigDecimal new_y){
            this.y = new_y;
            return this;
        }

        // Setter for collectionFactory and values???

        public final Updater setCoordinate(Coordinate coordinate){
            coordinate.validate();
            this.x = coordinate.x();
            this.y = coordinate.y();
            return this;
            // Will this return the outer class BiDimensionalMap or nested class Updater???

        }


        public final Updater addValue(T value){
            values.add(value);
            return this;
        }

        public final Collection<T> set(){
            try{
                tp1 = this.points.get(this.x);
                tp2 = tp1.get(this.y);
                res = tp2;
                tp2 = this.values;
                tp1.put(this.y,tp1);
                this.points.put(this.x,tp1);
                return res;
            }
            catch(NullPointerException e){
                return null;
            }
        }

        public final boolean add(){
            //
        }

    }

}

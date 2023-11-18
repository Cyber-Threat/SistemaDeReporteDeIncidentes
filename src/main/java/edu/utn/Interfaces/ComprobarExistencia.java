package edu.utn.Interfaces;
import java.util.Collection;
import java.util.function.Predicate;
@FunctionalInterface
public interface ComprobarExistencia<T> {
    boolean comprobar(Collection<T> c, Predicate<T> p);
}
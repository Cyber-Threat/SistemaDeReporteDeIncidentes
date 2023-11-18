package edu.utn.Interfaces;
import java.util.Collection;
import java.util.function.Predicate;
@FunctionalInterface
public interface BuscarObjeto<T> {
    T buscar(Collection<T> coleccion, Predicate<T> p);
}
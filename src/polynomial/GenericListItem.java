package polynomial;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Generic interface representing a node in the {@link List} interface.
 * Each node supports operations such as addition of new terms or updating them in-place given
 * certain criteria, removal of node/s with the matching data, counting etc. The node should also
 * support the map/filter/reduce higher order functions.
 *
 * @param <T> the type of data being stored in the list.
 */
interface GenericListItem<T> {

  /**
   * Returns the count of items in the sub-list from current item to the end.
   *
   * @param countSoFar the count of items seen before.
   * @return the number of items in the list, from current item to the end.
   */
  int count(int countSoFar);

  /**
   * Returns the data item at the specified position.
   *
   * @param position position of item in the list, starting from 0.
   * @return the data object at the position.
   * @throws IllegalArgumentException if the position doesn't exist in the list.
   */
  T get(int position) throws IllegalArgumentException;

  /**
   * Add the given data in a new node or update in-place in the list.
   * The order and position where the item will be added depends on the order decided by the
   * comparator provided as an argument. Also, if the comparator decides the input data object to
   * be equal to another existing object in the list, then the two are merged using the function
   * provided as the argument.
   *
   * @param data data to be inserted/updated in the list.
   * @param comparator object to compare items in list to decide ordering of items.
   * @param updateFunction function object to merge incoming object with existing object
   *         should they match.
   * @return list node item.
   */
  GenericListItem<T> addOrUpdate(T data, Comparator<T> comparator,
          BiFunction<T, T, T> updateFunction);

  /**
   * Removes the data object from the list, if it exists.
   *
   * @param data the data to remove.
   * @return the item that was removed.
   * @throws IllegalArgumentException if the data is not found in the list.
   */
  GenericListItem<T> remove(T data) throws IllegalArgumentException;

  /**
   * Returns a new list node consisting of the results of applying the given function to the
   * elements of this list.
   *
   * @param function a non-interfering, stateless function to apply to each element.
   * @param <R> The element type of the new stream.
   * @return the new node.
   */
  <R> GenericListItem<R> map(Function<T, R> function);

  /**
   * Returns a new list node consisting of the elements of this stream that match the given
   * predicate.
   *
   * @param predicate a non-interfering, stateless predicate to apply to each element to
   *         determine if it should be included
   * @return the new list node.
   */
  GenericListItem<T> filter(Predicate<T> predicate);

  /**
   * Performs a reduction on the elements of this list, using the provided identity value and
   * an associative accumulation function, and returns the reduced value.
   *
   * @param initVal the initial value to be used in reduction.
   * @param function function to map input to output type.
   * @param <R> The type of the result
   * @return object to above type.
   */
  <R> R reduce(R initVal, BiFunction<T, R, R> function);
}

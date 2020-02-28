package polynomial;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p>An ordered collection (also known as a sequence). The user of this interface can dictate the
 * ordering of the items in the list, but the actual ordering depends on the items in the list
 * themselves.</p>
 *
 * <p>More specifically, the list orders the items using the rules specified in the add method
 * of the interface. The user can control the ordering of items using the comparator provided to
 * the add method. Also, the list allows for in-place modification of the list. The user can
 * specify how 2 objects can be merged into a single object to update a node in-place.
 * </p>
 *
 * @param <T> type of data being stored in the list.
 */
public interface List<T> {

  /**
   * Returns the number of elements in this list. If this list contains more than Integer
   * .MAX_VALUE elements, the count overflows. Hence, this list is only suitable for storing
   * INTEGER.MAX_VALUE items.
   *
   * @return size of the list.
   */
  int size();

  /**
   * Returns the element at the specified position in this list.
   *
   * @param position index of the element to return
   * @return the element at the specified position in this list
   * @throws IllegalArgumentException if the position is not found in the list.
   */
  T get(int position) throws IllegalArgumentException;

  /**
   * Adds a new item in the list based on the order defined by the comparator. The idea is that
   * the list orders itself such that the items are sorted according to the order defined by the
   * comparator. Also, the list supports operations which require items to be updated in the list
   * in-place based on certain criteria. Specifically, if the item being inserted is the same as
   * the current item in the list, based on the criteria defined by the comparator, the list
   * implementation should update the contents of the current node instead of creating a new list
   * item.
   *
   * @param data data to be added to the list.
   * @param comparator comparator to compare data being added to items in the list.
   * @param updateFunction function to transform current data and input data into new data
   *         object, which will be placed at the specified node in the list.
   */
  void addOrUpdate(T data, Comparator<T> comparator, BiFunction<T, T, T> updateFunction);

  /**
   * Removes the element at the specified position in this list (optional operation). Shifts any
   * subsequent elements to the left (subtracts one from their indices). Returns the element that
   * was removed from the list.
   *
   * @param data the element previously at the specified position
   */
  void remove(T data);

  /**
   * Returns a list consisting of the results of applying the given function to the elements of
   * this stream.
   *
   * @param function a non-interfering, stateless function to apply to each element.
   * @param <R> The element type of the new stream.
   * @return the new list.
   */
  <R> List<R> map(Function<T, R> function);

  /**
   * Returns a list consisting of the elements of this stream that match the given predicate.
   *
   * @param predicate a non-interfering, stateless predicate to apply to each element to
   *         determine if it should be included
   * @return the new list.
   */
  List<T> filter(Predicate<T> predicate);

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

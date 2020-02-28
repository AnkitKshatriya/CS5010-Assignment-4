package polynomial;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This class serves as the terminator node in a recursive data structure. Also referred to as
 * the sentinel node.
 *
 * @param <T> the generic item which will be stored in the list.
 */
public class SinglyLinkedListTerminalNode<T> implements GenericListItem<T> {

  @Override
  public int count(int countSoFar) {
    return countSoFar;
  }

  @Override
  public T get(int position) {
    throw new IndexOutOfBoundsException("Cannot get item beyond the end of the list!");
  }

  @Override
  public GenericListItem<T> addOrUpdate(T data, Comparator<T> comparator,
          BiFunction<T, T, T> function) {
    return new SinglyLinkedListDataNode<T>(data, this);
  }

  @Override
  public GenericListItem<T> remove(T data) {
    throw new IllegalArgumentException("Could not remove requested data. Data not found in the "
            + "list");
  }

  @Override
  public <R> GenericListItem<R> map(Function<T, R> function) {
    return new SinglyLinkedListTerminalNode<>();
  }

  @Override
  public GenericListItem<T> filter(Predicate<T> predicate) {
    return new SinglyLinkedListTerminalNode<>();
  }

  @Override
  public <R> R reduce(R initVal, BiFunction<T, R, R> function) {
    return initVal;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    return obj instanceof SinglyLinkedListTerminalNode;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}

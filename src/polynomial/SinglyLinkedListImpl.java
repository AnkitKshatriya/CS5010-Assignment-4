package polynomial;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Singly linked list representation of the {@link List} interface. Given the list is stored
 * using a singly linked list, read/writes are O(n) while addition and multiplication are
 * quadratic and cubic respectively.
 *
 * @param <T> the type of data to store in the list.
 */
class SinglyLinkedListImpl<T> implements List<T> {

  private GenericListItem<T> head;

  SinglyLinkedListImpl() {
    this.head = new SinglyLinkedListTerminalNode<>();
  }

  private SinglyLinkedListImpl(GenericListItem<T> head) {
    this.head = head;
  }

  @Override
  public void addOrUpdate(T data, Comparator<T> comparator, BiFunction<T, T, T> updateFunction) {
    this.head = this.head.addOrUpdate(data, comparator, updateFunction);
  }

  @Override
  public void remove(T data) {
    this.head.remove(data);
  }

  @Override
  public T get(int position) {
    return this.head.get(position);
  }

  @Override
  public int size() {
    return this.head.count(0);
  }

  @Override
  public <R> List<R> map(Function<T, R> function) {
    SinglyLinkedListImpl<R> mappedList = new SinglyLinkedListImpl<>();
    mappedList.head = this.head.map(function);

    return mappedList;
  }

  @Override
  public List<T> filter(Predicate<T> predicate) {
    return new SinglyLinkedListImpl<>(this.head.filter(predicate));
  }

  @Override
  public <R> R reduce(R initVal, BiFunction<T, R, R> function) {
    return this.head.reduce(initVal, function);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (obj instanceof SinglyLinkedListImpl) {
      SinglyLinkedListImpl otherList = (SinglyLinkedListImpl) obj;
      if (otherList.size() == this.size()) {
        for (int i = 0; i < this.size(); i++) {
          if (!this.get(i).equals(otherList.get(i))) {
            return false;
          }
        }
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    int size = this.size();
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < size; i++) {
      builder.append(get(i).toString()).append(" ");
    }

    return builder.toString();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}

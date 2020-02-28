package polynomial;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Data node implementing the {@link GenericListItem} interface. This class uses singly linked
 * list to represent the list item.
 *
 * @param <T> the type of data being stored in the node.
 */
class SinglyLinkedListDataNode<T> implements GenericListItem<T> {

  private T data;
  private GenericListItem<T> rest;

  SinglyLinkedListDataNode(T data, GenericListItem<T> rest) {
    this.data = data;
    this.rest = rest;
  }

  @Override
  public int count(int countSoFar) {
    return this.rest.count(countSoFar + 1);
  }

  @Override
  public T get(int position) throws IllegalArgumentException {
    if (position == 0) {
      return this.data;
    } else {
      return this.rest.get(position - 1);
    }
  }

  /**
   * Since this node is used to implement ordered lists, it accepts a comparator. Also, the list
   * supports in-place update of items using another input. This is supported using the
   * BiFunction, which maps the current item in the list and the input into the final output
   * which is then stored in the node.
   *
   * @param data data to be added to existing data.
   * @param comparator comparator for comparing existing data with input data.
   * @param function function to transform current data and input to another value.
   * @return new node in case of addition of node, and updated node in case of data update.
   */
  @Override
  public GenericListItem<T> addOrUpdate(T data, Comparator<T> comparator,
          BiFunction<T, T, T> function) {
    int comparison = comparator.compare(this.data, data);
    if (comparison == 0) {
      this.data = function.apply(this.data, data);
      return this;
    } else if (comparison < 0) {
      return new SinglyLinkedListDataNode<>(data, this);
    } else {
      this.rest = this.rest.addOrUpdate(data, comparator, function);
      return this;
    }
  }

  @Override
  public GenericListItem<T> remove(T data) {
    if (this.data.equals(data)) {
      return this.rest;
    } else {
      this.rest = this.rest.remove(data);
      return this;
    }
  }

  @Override
  public <R> GenericListItem<R> map(Function<T, R> function) {
    return new SinglyLinkedListDataNode<>(function.apply(data), this.rest.map(function));
  }

  @Override
  public GenericListItem<T> filter(Predicate<T> predicate) {
    if (predicate.test(this.data)) {
      return new SinglyLinkedListDataNode<>(this.data, this.rest.filter(predicate));
    } else {
      return this.rest.filter(predicate);
    }
  }

  @Override
  public <R> R reduce(R initVal, BiFunction<T, R, R> function) {
    return this.rest.reduce(function.apply(this.data, initVal), function);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (obj instanceof SinglyLinkedListDataNode) {
      SinglyLinkedListDataNode otherNode = (SinglyLinkedListDataNode) obj;
      return this.data.equals(otherNode.data);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}

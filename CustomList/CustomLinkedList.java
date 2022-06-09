package com.dimas4ek.CustomList;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class CustomLinkedList<Element> implements Iterable<Element> {
    private Node<Element> first;
    private Node<Element> last;
    private int size;

    public int size() {
        return size;
    }
    public void add(Element element) {
        Node<Element> node;
        if(first==null) {
            node = new Node<>(element, null, null);
            first = node;
        } else {
            node = new Node<>(element, null, last);
            last.next = node;
        }
        last = node;
        size++;
    }

    public void addFirst(Element element) {
        Node<Element> newFirstElement = new Node<>(element, first, null);
        newFirstElement.next = first;
        //Node<Element> tmp = first;
        //tmp.prev = newFirstElement;
        first = newFirstElement;
        size++;
    }

    public Element getFirst() {
        return first.item;
    }
    public Element getLast() {
        return last.item;
    }

    public Element get(int index) {
        return getNodeByIndex(index).item;
    }

    private Node<Element> getNodeByIndex(int index) {
        Node<Element> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    public void remove(int index) {
        Node<Element> deleteNode = getNodeByIndex(index);
        //deleteNode.prev.next.next = deleteNode.next;
        Node<Element> elementBefore = deleteNode.prev;
        Node<Element> elementAfter = deleteNode.next;
        elementBefore.next = elementAfter;
        elementAfter.prev = elementBefore;
        size--;
    }

    @NotNull
    @Override
    public Iterator<Element> iterator() {
        return new Iterator<>() {

            private Node<Element> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Element next() {
                Node<Element> tmp = current;
                current = current.next;
                return tmp.item;
            }
        };
    }

    private static class Node<Element> {
        Element item;
        Node<Element> next;
        Node<Element> prev;

        public Node(Element item, Node<Element> next, Node<Element> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}

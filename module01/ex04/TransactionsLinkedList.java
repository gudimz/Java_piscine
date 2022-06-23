package module01.ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private TransactionNode begin;
    private TransactionNode end;
    private int size;

    TransactionsLinkedList() {
        begin = null;
        end = null;
        size = 0;
    }

    public TransactionNode getBegin() {
        return begin;
    }

    public void setBegin(TransactionNode begin) {
        this.begin = begin;
    }

    public TransactionNode getEnd() {
        return end;
    }

    public void setEnd(TransactionNode end) {
        this.end = end;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            return;
        }
        if (begin == null) {
            begin = new TransactionNode(transaction, null, null);
            end = begin;
        } else {
            TransactionNode node = new TransactionNode(transaction, null, end);
            end.setNext(node);
            end = node;
        }
        size++;
    }

    @Override
    public void removeById(UUID id) throws TransactionNotFoundException {
        TransactionNode tmp = begin;
        while (tmp != null) {
            if (tmp.getData().getId().equals(id)) {
                if (tmp.getNext() == null && tmp.getPrev() == null) {
                    begin = null;
                    end = null;
                } else {
                    if (tmp.getPrev() == null) {
                        begin = tmp.getNext();
                        begin.setPrev(null);
                    } else {
                        tmp.getPrev().setNext(tmp.getNext());
                    }
                    if (tmp.getNext() == null) {
                        end = tmp.getPrev();
                        end.setNext(null);
                    } else {
                        tmp.getNext().setPrev(tmp.getPrev());
                    }
                }
                size--;
                return;
            }
            tmp = tmp.getNext();
        }
        throw new TransactionNotFoundException("Transaction with ID: " + id + " not found!");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] result = new Transaction[getSize()];
        TransactionNode tmp = begin;

        if (tmp != null) {
            for (int i = 0; i < result.length; i++) {
                result[i] = tmp.getData();
                tmp = tmp.getNext();
            }
        }
        return result;
    }

    private static class TransactionNode {
        private Transaction data;
        private TransactionNode next;
        private TransactionNode prev;

        TransactionNode(Transaction data, TransactionNode next, TransactionNode prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Transaction getData() {
            return data;
        }

        public void setData(Transaction data) {
            this.data = data;
        }

        public TransactionNode getNext() {
            return next;
        }

        public void setNext(TransactionNode next) {
            this.next = next;
        }

        public TransactionNode getPrev() {
            return prev;
        }

        public void setPrev(TransactionNode prev) {
            this.prev = prev;
        }
    }
}

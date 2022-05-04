package ru.job4j.design.lsp;

import java.util.List;

public class Example {

    class Order {
        private int sum;
        private final String customer;
        private List<String> items;
        private int sumHold = 0;

        public Order(int sum, String customer, List<String> items) {
            this.sum = sum;
            this.customer = customer;
            this.items = items;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }

        public int getSumHold() {
            return sumHold;
        }

        public void setSumHold(int sumHold) {
            this.sumHold = sumHold;
        }

        @Override
        public String toString() {
            return "Order{" + "sum=" + sum + ", customer='" + customer + '\'' + ", items=" + items + '}';
        }
    }

    class CustomerOrder {
        public boolean payOrder(Order order) {
            boolean rst = false;
            if (new BeforePayOrderValidator().isValid(order)) {
                rst = new PaymentOrder().create(order);
            }
            if (rst) {
                rst = new AfterParOrderValidator().isValid(order);
            }
            return rst;
        }
    }

    class BeforePayOrderValidator {
        public boolean isValid(Order order) {
            boolean rst = true;

            if (order.getSum() == 0 || order.getItems().size() == 0) {
                rst = false;
            }

            return rst;
        }
    }

    class BeforePayCustomerOrderValidator extends BeforePayOrderValidator {
        @Override
        public boolean isValid(Order order) {
            boolean rst = false;

            /**
             * 1. Нарушение LSP, усиление предусловия в подклассе.
             *  В результате, если родительский класс заменить наследником, то поведение изменится
             */
            if (order.getSum() < 500 || order.getItems().size() == 0) {
                /**
                 * 3. Наследники при переопределении метода родительского класса,
                 *  должны не нарушать функциональность с точки зрения клиента
                 */
                throw new IllegalStateException("The order value is too low");
            }
            return rst;
        }
    }

    class PaymentOrder {
        public boolean create(Order order) {
            /**
             * логика метода оплаты
             */
            return true;
        }
    }

    public class AfterParOrderValidator {
        public boolean isValid(Order order) {
            return order.getSum() == order.getSumHold();
        }
    }

    public class AfterParCustomerOrderValidator extends AfterParOrderValidator {
        @Override
        public boolean isValid(Order order) {
            /**
             * 2. Нарушение LSP, ослабление постусловия в подклассе
             *  В результате, если родительский класс заменить наследником, то поведение изменится
             */
            return order.getSum() >= order.getSumHold()
                    && order.getSumHold() != 0;
        }
    }
}

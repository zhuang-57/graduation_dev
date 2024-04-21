package com.example.h_item.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * @author fangming.yi
 * @version 1.0
 * @since 2020/10/20 20:43
 */
public class Sort implements Serializable {

    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private static final long serialVersionUID = -4717615485715830276L;

    private List<Order> orders;

    protected Sort() {
        // 避免ImmutableList.of()引起Hessian序列化问题
        this(new ArrayList<>(0));
    }

    public Sort(Order... orders) {
        this(Arrays.asList(orders));
    }

    @JsonCreator
    public Sort(@JsonProperty("orders") List<Order> orders) {
        Assert.notNull(orders, "You have to provide sort property to sort by!");
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Order getOrderByProperty(String property) {
        for (Order order : orders) {
            if (order.getProperty().equals(property)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sort)) return false;

        Sort sort = (Sort) o;

        return orders != null ? orders.equals(sort.orders) : sort.orders == null;
    }

    @Override
    public int hashCode() {
        return orders != null ? orders.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "orders=" + orders +
                '}';
    }

    /**
     * 排序方式。
     *
     * @author fangming.yi
     * @version 1.0
     * @since 2020/11/3 9:16
     */
    public enum Direction {
        /**
         *
         */
        ASC,
        DESC;

        public static Direction fromString(String value) {

            try {
                return Direction.valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format(
                        "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
            }
        }

        public static Direction fromStringOrNull(String value) {

            try {
                return fromString(value);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    /**
     * 排序条件。
     *
     * @author fangming.yi
     * @version 1.0
     * @since 2020/11/3 9:15
     */
    public static class Order implements Serializable {

        private static final long serialVersionUID = -1212865797767706224L;

        private Direction direction = DEFAULT_DIRECTION;

        private String property;

        protected Order() {
        }

        public Order(String property) {
            this(null, property);
        }

        @JsonCreator
        public Order(@JsonProperty("direction") Direction direction, @JsonProperty("property") String property) {
            if (direction != null) {
                this.direction = direction;
            }
            this.property = property;
        }

        public static List<Order> create(Direction direction, Collection<String> properties) {
            List<Order> orders = Lists.newArrayListWithCapacity(properties.size());
            for (String property : properties) {
                orders.add(new Order(direction, property));
            }
            return orders;
        }

        public Direction getDirection() {
            return direction;
        }

        public String getProperty() {
            return property;
        }

        @JsonIgnore
        public boolean isAscending() {
            return this.direction.equals(Direction.ASC);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Order)) return false;

            Order order = (Order) o;

            if (direction != order.direction) return false;
            return property.equals(order.property);
        }

        @Override
        public int hashCode() {
            int result = direction.hashCode();
            result = 31 * result + property.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "direction=" + direction +
                    ", property='" + property + '\'' +
                    '}';
        }
    }
}
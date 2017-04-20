package com.zhaokxkx13.dao.entity;

import java.io.Serializable;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
public class ProductOrder implements Serializable {
    private Integer id;
    private Product product;
    private Order order;
    private Integer num;
    private Double price;
    private Double receivable;
    private Double collected;
    private Integer orderId;

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", product=" + product +
                ", order=" + order +
                ", num=" + num +
                ", price=" + price +
                ", receivable=" + receivable +
                ", collected=" + collected +
                ", orderId=" + orderId +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets receivable.
     *
     * @return the receivable
     */
    public Double getReceivable() {
        return receivable;
    }

    /**
     * Sets receivable.
     *
     * @param receivable the receivable
     */
    public void setReceivable(Double receivable) {
        this.receivable = receivable;
    }


    /**
     * Gets collected.
     *
     * @return the collected
     */
    public Double getCollected() {
        return collected;
    }

    /**
     * Sets collected.
     *
     * @param collected the collected
     */
    public void setCollected(Double collected) {
        this.collected = collected;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets num.
     *
     * @return the num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * Sets num.
     *
     * @param num the num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

}

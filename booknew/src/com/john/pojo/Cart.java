package com.john.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author John
 * @create 2021-09-2721:18
 */
public class Cart {
    public Integer getTotalCount() {
        totalCount = 0;
        for (Map.Entry<Integer, CartItem>entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }



    public BigDecimal getTotalPrice() {
        totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem>entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    private Integer totalCount;
    private BigDecimal totalPrice;
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    /**
     * ������Ʒ��
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        // �Ȳ鿴���ﳵ���Ƿ�����Ӵ���Ʒ�����Ѿ���ӣ��������ۼӣ��ܽ����£����û����ӹ���ֱ�ӷŵ������м���
        CartItem item = items.get(cartItem.getId());
        if (item == null){
            items.put(cartItem.getId(), cartItem);
        }else {
            //�Ѿ���ӹ������
            item.setCount( item.getCount() + 1); // �����ۼ�
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount()))); // �����ܽ��
        }
    }

    /**
     * ɾ����Ʒ��
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * ��չ��ﳵ
     */
    public void clear(){
        items.clear();
    }
    /**
     * �޸���Ʒ����
     */
    public void updateCount(Integer id, Integer count){
        // �鿴���ﳵ�Ƿ��д���Ʒ������У��޸���Ʒ�����������ܽ��

        CartItem cartItem = items.get(id);
        if (cartItem != null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount()))); // �����ܽ��
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

}

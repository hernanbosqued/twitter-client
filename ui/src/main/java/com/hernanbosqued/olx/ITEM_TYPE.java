package com.hernanbosqued.olx;

/**
 * Created by hernan on 14/07/17.
 */
enum ITEM_TYPE {
    ROW_EVEN(0),
    ROW_ODD(1);

    private int itemType;

    ITEM_TYPE(int itemType) {
        this.itemType = itemType;
    }

    public static ITEM_TYPE valueOf(int itemType) {
        for (ITEM_TYPE item : ITEM_TYPE.values()) {
            if (item.itemType == itemType) {
                return item;
            }
        }
        return ROW_EVEN;
    }
}

package com.example.listaproductos.Activities;

public class UpdateAdapter {

    private int updateStatus;
    private int updateItemIndex;
    private int updateItemId;

    public UpdateAdapter(){
        setAdapter();
    }

    public int getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(int updateStatus) {
        this.updateStatus = updateStatus;
    }

    public int getUpdateItemIndex() {
        return updateItemIndex;
    }

    public void setUpdateItemIndex(int updateItemIndex) {
        this.updateItemIndex = updateItemIndex;
    }

    public int getUpdateItemId() {
        return updateItemId;
    }

    public void setUpdateItemId(int updateItemId) {
        this.updateItemId = updateItemId;
    }

    public void setAdapter(){
        this.updateItemIndex = -1;
        this.updateStatus = 0;
        this.updateItemId = 0;
    }
}

package com.capricorn.yummy.yummywakeup.infrastructure.activity;

/**
 * Created by chuandong on 15/8/26.
 */
public interface InitActivity {

    /**
     * Initializes Toolbar
     */
    public abstract void initToolbar();

    /**
     * Initializes Views
     */
    public abstract void initView();

    /**
     * Initializes Listener
     */
    public abstract void initListener();

    /**
     * Initializes Data
     */
    public abstract void initData();

    /**
     * Get layout Id of activity
     * @return layout id
     */
    public abstract int getLayoutId();
}

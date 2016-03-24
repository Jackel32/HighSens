package com.highsens.game;

public class ContextStrategy {
    
    IStrategy strategy;
    
    public ContextStrategy(IStrategy strategy)
    {
            this.strategy = strategy;
    }
    
    public IStrategy strategy()
    {
            return strategy;
    }

    public int Score()
    {
        return this.strategy.getScore();
    }
    
}

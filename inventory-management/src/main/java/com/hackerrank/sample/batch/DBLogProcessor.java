package com.hackerrank.sample.batch;
 
import org.springframework.batch.item.ItemProcessor;

import com.hackerrank.sample.model.Sku;
 
public class DBLogProcessor implements ItemProcessor<Sku, Sku>
{
    public Sku process(Sku inventory) throws Exception
    {
        System.out.println("Inserting employee : " + inventory);
        return inventory;
    }
}
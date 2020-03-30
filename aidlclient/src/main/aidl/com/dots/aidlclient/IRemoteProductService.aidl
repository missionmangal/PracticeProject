// IRemoteProductService.aidl
package com.dots.aidlclient;
import com.dots.aidlclient.Product;
// Declare any non-default types here with import statements

interface IRemoteProductService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
            void addProduct(String name,int quantity,float cost);
//            String getProduct(String name);
            Product getProduct(String name);

}

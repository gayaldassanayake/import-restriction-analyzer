import gayaldassanayake/import_restrictor as _;

import ballerina/log;
import ballerinax/nats;

import gayaldassanayake/inventory_manager;

public type Order record {
    int orderId;
    string productName;
    decimal price;
    boolean isValid;
};

// Binds the consumer to listen to the messages published to the 'orders.valid' subject.
service "orders.valid" on new nats:Listener(nats:DEFAULT_URL) {

    remote function onMessage(Order 'order) returns error? {
        if 'order.isValid {
            log:printInfo(string `Received valid order for ${'order.productName}`);
            inventory_manager:acceptOrder('order);
        }
    }
}

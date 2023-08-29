import ballerina/log;

public type Order record {
    int orderId;
    string productName;
    decimal price;
    boolean isValid;
};

public function acceptOrder(Order 'order) {
    log:printInfo( string `Order: ${'order.orderId} has been accepted.`);
}

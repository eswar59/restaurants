

//const stompClient = new StompJs.Client({
//    brokerURL: 'ws://localhost:8000/ws'
//});

function onConnect (frame)  {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/user/queue/orders', (order) => {
        console.log("we are in dimma");
        console.log("---------");
        console.log(order);
        console.log("999999999999");
        console.log(JSON.parse(order.body).content);
        showOrder(order.body);
    });
    stompClient.send("/app/restaurantStatusChange",
            {},
            JSON.stringify({'status': 'OPENED'})
    )
//    stompClient.publish({
//        destination: "/app/restaurantStatusChange",
//        body: JSON.stringify({'status': 'OPENED'})
//    });
}

function onWebSocketError (error) {
    console.error('Error with websocket', error);
}

function onStompError(frame){
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
}

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnect, onError);
}
function onError(e){
    console.log(e);
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    stompClient.send("/app/restaurantStatusChange",
            {},
            JSON.stringify({'status': 'CLOSED'})
    )

//    stompClient.publish({
//        destination: "/app/restaurantStatusChange",
//        body: JSON.stringify({'status': 'CLOSED'})
//    });
    console.log("Disconnected");
}

//make changes so that they can send back the status back to restaurant
function sendOrderBackToRestaurant() {
    stompClient.send("/app/modifiedOrder",
            {},
            JSON.stringify({'status': $("#name").val()})
    )
//    stompClient.publish({
//        destination: "/app/modifiedOrder",
//        body: JSON.stringify({'status': $("#name").val()})
//    });
}

function showOrder(order) {
    $("#greetings").append("<tr><td>" + order + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendOrderBackToRestaurant());
});
(function() {
    var websocket;

    console.log("Websocket script was loaded");

    window.received = [];

    window.websocket = new WebSocket("ws://" + window.location.host + "/websocket");

    window.websocket.onmessage = function(msg) {
        var json;
        console.log("Received a message over the websocket:");
        console.log(msg);
        console.log("---");
        json = JSON.parse(msg.data);
        window.received.push(json);
        return rerender();
    };

    window.websocket.onopen = function() {
        //return alert("Connection with server open.");
    };

    // window.websocket.send = function(msg) {
    //     return JSON.stringify(msg);
    // };

}).call(this);
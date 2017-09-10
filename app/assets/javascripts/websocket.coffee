console.log("Websocket script was loaded")

websocket = new WebSocket("ws://#{window.location.host}/websocket");

websocket.onmessage = (msg) ->
  console.log("Received a message over the websocket:")
  console.log(msg)
  console.log("---")
  json = JSON.parse(msg.data)
  #window.received.push(json)
  rerender()

# Called when the connection to the server is opened.
websocket.onopen =  () ->
  alert("Connection with server open.");

websocket.send = (msg) ->
  JSON.stringify(msg)


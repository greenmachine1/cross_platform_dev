
// Use Parse.Cloud.define to define as many cloud functions as you want.
// For example:
Parse.Cloud.define("hello", function(request, response) {
  response.success("This is JavaScript code that gets run on the cloud");
});






Parse.Cloud.afterSave("Post", function(request){
	
	var name = request.object.get('bandName');

	var pushQuery = new Parse.Query(Parse.Installation);
	//pushQuery.equalTo('deviceType', 'ios');
	
	Parse.Push.send({
		where: pushQuery,
		data: {
			alert: "" + name
		}
	},{
	sucess: function(){
		// push was successful
	},
	error: function(error){
		throw "Got an error " + error.code + " : " + error.message;
	}
	});
	
});


Parse.Cloud.afterDelete("Post", function(request){

	var pushQuery = new Parse.Query(Parse.Installation);
	//pushQuery.equalTo('deviceType', 'ios');
	
	Parse.Push.send({
		where: pushQuery,
		data: {
		}
	},{
	sucess: function(){
		// push was successful
	},
	error: function(error){
		throw "Got an error " + error.code + " : " + error.message;
	}
	});
});

var app = angular.module('MyApp', [])

// var apiCategory = {}
// apiCategory.list = function() {
	
// }

app.service('apiCategory', ['$http', function ($http) {
	this.list = function(callback) {
		$http.get(API_CATEGORY_LIST)
		  .then(function(response) {
		    callback(response.data)
		});
	}

	this.delete = function(data) {
		var option = confirm('Are you sure to delete this category?')
		if(!option) return

		$http.post(API_CATEGORY_DELETE, data)
		.then(function(response) {
		    if(response.data.status != 1) {
		    	alert(response.data.message)
		    } else {
		    	location.reload()
		    }
		});
	}

	this.add = function(data) {
		$http.post(API_CATEGORY_ADD, JSON.stringify(data))
		.then(function(response) {
		    if(response.data.status != 1) {
		    	alert(response.data.message)
		    } else {
		    	window.open('index.html', '_self')
		    }
		});
	}

	this.update = function(data) {
		$http.post(API_CATEGORY_EDIT, JSON.stringify(data))
		.then(function(response) {
		    if(response.data.status != 1) {
		    	alert(response.data.message)
		    } else {
		    	window.open('index.html', '_self')
		    }
		});
	}

	this.find = function(id, callback) {
		$http.get(API_CATEGORY_FIND + id)
		  .then(function(response) {
		  	callback(response.data.name)
		  });
	}
}])
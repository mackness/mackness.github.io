
var App = {

	handleFormSubmission: function() {
		var data = {
			"name" : document.querySelector('.contact-form__input').value ,
			"message" : document.querySelector('.contact-form__textarea').value
		}
		// var xhr = new XMLHttpRequest();
		// xhr.open("POST", "https://e8h533pg98.execute-api.us-west-2.amazonaws.com/dev/mailer", true);
		// xhr.setRequestHeader("Content-type", "application/json");
		// xhr.send(params);
		function xdr(url, method, data, callback, errback) {
		    var req;
		    
		    if(XMLHttpRequest) {
		        req = new XMLHttpRequest();
		        if('withCredentials' in req) {
		            req.open(method, url, true);
		        	req.setRequestHeader("Content-type", "application/json");
		            req.onerror = errback;
		            req.onreadystatechange = function() {
		                if (req.readyState === 4) {
		                    if (req.status >= 200 && req.status < 400) {
		                        callback(req.responseText);
		                    } else {
		                        errback(new Error('Response returned with non-OK status'));
		                    }
		                }
		            };
		            req.send(data);
		        }
		    } else if(XDomainRequest) {
		        req = new XDomainRequest();
		        req.open(method, url);
				req.setRequestHeader("Content-type", "application/json");
		        req.onerror = errback;
		        req.onload = function() {
		            callback(req.responseText);
		        };
		        req.send(data);
		    } else {
		        errback(new Error('CORS not supported'));
		    }
		}

		xdr('https://e8h533pg98.execute-api.us-west-2.amazonaws.com/dev/mailer', 'POST', data, function(res){console.log(res)}, function(res){console.log(res)})
	},
	
	handleFormVis: function() {
		var form = document.querySelector('#contact-form')
		form.classList.contains('hidden') ? form.classList.remove('hidden') : form.classList.add('hidden')
	},

	init: function() {
		document.querySelector('.nav__menu__anchor--contact').addEventListener('click', function(event) {
			event.preventDefault();
			this.handleFormVis();
		}.bind(this));

		document.querySelector('#contact-form').addEventListener('submit', function(event) {
			event.preventDefault();
			this.handleFormSubmission()
		}.bind(this))
	}

}


window.onload = App.init()

var App = {
	handleFormSubmission: function(data) {

		var data = {
			"name" : document.querySelector('.contact-form__input--name').value,
			"email" : document.querySelector('.contact-form__input--email').value,
			"message" : document.querySelector('.contact-form__textarea').value
		}

		function req(url, data, callback, errback) {
			var request = new XMLHttpRequest();
			request.open('POST', url, true);
			request.setRequestHeader('Content-Type', 'application/json');
			
			request.send(JSON.stringify(data));
		}

		req('https://e8h533pg98.execute-api.us-west-2.amazonaws.com/dev/mailer', data, function(res) {
			console.log(res)
		}, function(err) {
			console.log('that is an error:', err)
		})
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
			this.handleFormSubmission();
		}.bind(this))
	}
}

window.onload = App.init()
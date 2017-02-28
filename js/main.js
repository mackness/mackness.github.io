
var App = {
	
	handleFormSubmission: function(data) {

		var data = {
			"site_id": "mackness.github.io",
			"send_to": "macksol@gmail.com",
			"email_data": {
				"name" : name.value,
				"email" : email.value,
				"message" : message.value
			} 
		}

		function req(url, data, cb, eb) {
			var request = new XMLHttpRequest();
			request.open('POST', url, true);
			request.setRequestHeader('Content-Type', 'application/json');
			request.onerror = eb;
            request.onreadystatechange = function() {
                if (request.readyState === 4) {
                    if (request.status >= 200 && request.status < 400) {
                        cb(request.responseText);
                    } else {
                        cb(new Error('Response returned with non-OK status'));
                    }
                }
            };
			request.send(JSON.stringify(data));
		}

		req('https://a7zjd97z6l.execute-api.us-west-2.amazonaws.com/prod/send', data, function(res) {
			submit.innerHTML = 'Got it, thanks!'
			setTimeout(function() {
				this.toggleForm();
				form.reset();
				submit.innerHTML = 'Submit'
			}.bind(this), 1750)
		}.bind(this), function(err) {
			console.error('that\'s an error ):', err)
		})
	},
	
	toggleForm: function() {
		form.classList.contains('hidden') ? form.classList.remove('hidden') : form.classList.add('hidden')
	},

	init: function(elements) {
		formTrigger = elements.formTrigger;
		form = elements.form;
		name = elements.name;
		email = elements.email;
		message = elements.message;
		submit = elements.submit;

		formTrigger.addEventListener('click', function(event) {
			event.stopPropagation();
			event.preventDefault();
			this.toggleForm();
		}.bind(this));

		form.addEventListener('submit', function(event) {
			event.preventDefault();
			this.handleFormSubmission();
		}.bind(this))

		form.addEventListener('click', function(event) {
			event.stopPropagation();
		})

		document.body.addEventListener('click', function() {
			if (!form.classList.contains('hidden')) {
				form.classList.add('hidden')
			}
		})
	}
}

window.onload = App.init({
	"formTrigger": document.querySelector('.nav__menu__anchor--contact'),
	"form": document.querySelector('#contact-form'),
	"name": document.querySelector('.contact-form__input--name'),
	"email": document.querySelector('.contact-form__input--email'),
	"message": document.querySelector('.contact-form__textarea'),
	"submit": document.querySelector('.contact-form__submit')
})





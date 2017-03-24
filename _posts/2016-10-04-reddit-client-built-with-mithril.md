---
layout: post
title:  "React Reddit Client v.s. Mithril Reddit Client"
date:   2016-10-04
categories: mithril react
---

I like React. I like Mithril. I wanted to compare them to figure out which one made me happier. This comparison will not take a scientific approach to measuring performance. Rather, it's designed to measure general developer happiness. I'll go over some of the things that made me happy and some of the pain points with both systems.

## Mithril

<p data-height="265" data-theme-id="light" data-slug-hash="vXAwAX" data-default-tab="js,result" data-user="mackness" data-embed-version="2" class="codepen">See the Pen <a href="https://codepen.io/mackness/pen/vXAwAX/">mithril reddit</a> by mackness (<a href="http://codepen.io/mackness">@mackness</a>) on <a href="http://codepen.io">CodePen</a>.</p>
<script async src="//assets.codepen.io/assets/embed/ei.js"></script>


I look at Mithril as a small no frills MVC framework. It seems extremely light and doesn't feel like there is a ton of magic going on behind the scenes. It makes me nervous when there's a lot going on in the background that I can't explain to myself so Mithril's simplicity is definitely refreshing. 

I'm gonna flat out list pros and cons.

### Pros:

 - Mithril has really convenient built in routing and request objects ([m.request](http://mithril.js.org/mithril.request.html) and [m.route](http://mithril.js.org/mithril.route.html)). It felt great to be able to use those two things without having to install a dependency. 
 - [m()](http://mithril.js.org/mithril.html) template system is great once you get used to it. Again, out of the box this just works without having to configure a transpiler or add another dependency.
 - Module structure is really simple. It's just an object with a controller and view property. Looks like this:
{% highlight js %}
var Component = {
    //controller
    controller: function() {},
    //view
    view: function(controller) {}
};
{% endhighlight %}

### Cons:
- Small community.
- No browser based dev tools :(
- Lacking examples and tutorials from the community.


## React

<p data-height="265" data-theme-id="light" data-slug-hash="RGJmXV" data-default-tab="js,result" data-user="mackness" data-embed-version="2" class="codepen">See the Pen <a href="https://codepen.io/mackness/pen/RGJmXV/">react reddit</a> by mackness (<a href="http://codepen.io/mackness">@mackness</a>) on <a href="http://codepen.io">CodePen</a>.</p>
<script async src="//assets.codepen.io/assets/embed/ei.js"></script>

## Pros:

- I think React's lifecycle methods are pretty convenient.
- Awesome community with lots of support and tools.
- Lots of tutorials and documentation.

## Cons:
- Pretty verbose syntax especially if you opt out of JSX.
- Requires dependencies to handle things you would kind of expect to be baked in.
- scope of things it is expected to handle relative to file size is petty bad.

## Conclsion

I like Mithril. It's really small and easy to use. If you compare the file size of both libs 145.33kb (React) 19.99kb (Mithril) it's even more impressive what Mithril is capable of right out of the box. I think the file size metric is a really important consideration for a client size library and Mithril is a powerhouse relative to it's file size. This quote from Mithril's docs really stood out for me:

> Note also that, despite having a bigger scope, Mithril has a smaller file size than React.

At the end of the day I think the most important thing is how comfortable you feel with a particular system and how easy it is for you and your team to manage complexity at scale. I feel really comfortable with React. I've build a couple things with it and I've gotten used to it's idiosyncrasies. I'm sure I could achieve the same level of comfort with Mithril but I would need a set of really convincing reasons to make the switch.





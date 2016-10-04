---
layout: post
title:  "Easy static site hosting on S3 with GIT deployment!"
date:   2016-09-08	 20:57:02 -0700
categories: aws hosting
---

Let's walk thought he process of deploying static code to an S3 bucket with git!

## Create an S3 bucket

1. Log into the aws console by clicking sign into the console button on the [aws homepage](https://aws.amazon.com/).
2. Then click [S3](https://console.aws.amazon.com/s3/) under storage &amp; content delivery.
3. Then click the create bucket button at the top left corner of the page and enter a bucket name. Bucket name needs to be unique (names are shared by all users of the system).
4. Then click the properties button in the top right corner of the screen. Under static website hosting choose enable static hosting.
5. Specify an index and error document.

## Deployment with [Git-s3](https://github.com/schickling/git-s3)

Unfortunately at the time of writing you can't install Git-S3 [with brew](https://github.com/schickling/git-s3#coming-soon).
So we'll use [Composer](https://github.com/schickling/git-s3/blob/master/doc/COMPOSER.md):

#### Download Composer
{% highlight bash %}
$ curl -sS https://getcomposer.org/installer | php
$ mv composer.phar /usr/local/bin/composer 
{% endhighlight %}

#### Add Composer to your path
find your `.bashrc`, `.bash_profile` or `.zshrc` file and add the following line to it
{% highlight bash %}
PATH=$HOME/.composer/vendor/bin:$PATH
{% endhighlight %}

#### Install Git-S3
{% highlight bash %}
composer global require schickling/git-s3:dev-master
{% endhighlight %}

Errors? [try this](https://github.com/schickling/git-s3/blob/master/doc/COMPOSER.md#troubleshooting)

Awesome, so at this point we have Git-S3 installed but before we can use it we're going to need an IAM role.

#### Create an IAM role 

1. Go back to the AWS console and click on the [IAM](https://console.aws.amazon.com/iam/home) link in the services menu at the top left of the screen.
2. Click the users link in the menu on the left.
3. Then click create new users button at the top left.
4. Enter a name for the user and click create at the bottom left.
5. Then click download credentials at the bottom right.

This will download a file called credentials.csv with the secret access key ID and the access key for the user you just created.
Make sure to store this in a safe off line place. At this point we have all the info we need to configure Git-s3. Go the the root of your 
project and initialize a git repo if one doesn't already exist.

{% highlight bash %}
git init
{% endhighlight %}

Then run

{% highlight bash %}
git-s3 configure
{% endhighlight %}

It will prompt you for some info. Your configuration will look something like this:

* key: key_id
* secret: secret_access_key
* region: us-west-1
* bucket: your_bucket_name
* path: ./

When the config successfully finishes you're ready to deploy code! Git-s3 will only deploy 
recently changed files so commit some code and run:

{% highlight bash %}
git-s3 deploy
{% endhighlight %}





















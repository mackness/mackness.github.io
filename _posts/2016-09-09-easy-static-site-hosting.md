---
layout: post
title:  "Easy static site hosting on S3 with GIT deployment!"
date:   2016-09-08	 20:57:02 -0700
categories: jekyll update
---

Let's walk thought he process of deploying static code to an s3 bucket with git!

### Create an s3 bucket

1) Log into the aws console by clicking sign into the console button on the [aws homepage](https://aws.amazon.com/).

2) Then click [s3](https://console.aws.amazon.com/s3/) under storage &amp; content delivery.

3) Then click the create bucket button at the top left corner of the page and enter a bucket name. Bucket name needs to be unique (names are shared by all users of the system).

Great, we've created a bucket.

### Configure GIT deployment using [Git-s3](https://github.com/schickling/git-s3)

Git-s3 relies on [Composer](https://github.com/schickling/git-s3/blob/master/doc/COMPOSER.md) as a dependency management tool (unfortunately, at the time of writing you can't install it with brew, [see this](https://github.com/schickling/git-s3#coming-soon)). So lets install 
and configure that with these commands if you're on a Mac:

#### Download Composer
```
$ curl -sS https://getcomposer.org/installer | php
$ mv composer.phar /usr/local/bin/composer 
```

#### Add Composer to your path
find your .bashrc, .bash_profile or .zshrc file and add the following line to it
```
PATH=$HOME/.composer/vendor/bin:$PATH
```

#### Install Git-S3
```
composer global require schickling/git-s3:dev-master
```

Errors? [try this](https://github.com/schickling/git-s3/blob/master/doc/COMPOSER.md#troubleshooting)

Awesome so at this point we have Git-S3 installed and ready to use, so let's give it a shot by cd











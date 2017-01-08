# Invisible College
An Android app for autonomous, self-organizing communities of learners.
We're most interested in teaching kids how to read and write in developing countries,
as part of the [Global Learning XPRIZE](http://learning.xprize.org).

The Invisible College is an idea that a free thinking group of friends and collaborators can teach themselves.
Our app demonstrates the use of several reusable components that we hope will be useful.
These are described below in their own repos, which are imported into the main repo as git submodules / Android Studio modules / gradle apps.

We are targeting API 22 to be compatible with the Kindle Fire (a $50 7" tablet from Amazon subsidized by advertisements).
We are also testing this with a Moto G First Generation (an older $200 smartphone) and a Pixel C (a modern $800 tablet).

## Building

Clone this repo recursively to pick up all the submodules.

Our development environment consists of:
* Android Studio (official IDE)
* Genymotion (fast emulator, free for personal use)

## SoundGrams

This is our first module.

We use a soundgram as a basic unit of learning, a letter, word, or phrase. It has three parts
* written: what the letter, word, or phrase looks like
* pictorial: a picture or video representing the word or phrase
* audio: a collection of sound samples recorded by users.

Our library contains code to record soundgrams, play them back, upvote samples, and combine soundgrams into compound structures.
For example, letter soundgrams can be combined to form a word soundgram.
Word soundgrams can be combined into a phrase or a sentence soundgram.

The app comes seeded with soundgrams contributed from the Invisible College team as well as friendly people all around the world.

## Transporter (to be implemented)

A Bluetooth Low Energy (BLE) library for two nearby devices to exchange data with each other when they are in close proximity.
It is used in this app to exchange soundgrams between two tablets of students who are socializing and becoming friends in real life.

## Symmetry (to be implemented)

We introduce the notion of a symmetry-breaking blockchain as a way of distributing a large body of data over
many devices which start with identical, compressed system images. The devices all have a short unique ID,
and the system images are full of compressed data (soundgrams in our case). Using the device ID as a seed,
Symmetry selectively deletes certain blocks of the compressed data, enough to decompress the remaining portion.

Through exchanging Soundgrams using Transporter above, the entire community of devices will eventually have
access to the entire original body of data. Allowing users to modify and create new Soundgrams will allow this
body of data to change over time.

While we call this a blockchain, we lean heavily towards availability of data versus consensus.
It is more important for students to have access to a lot of training examples, and to create and share new ones,
than for all students to learn the same material. We are interested in the collective learning of the entire
group. We believe this uneven distribution of data will benefit the varied interests and initial abilities of human students themselves.

## License

This source code is released as open source under the [BSD 3-clause license](https://opensource.org/licenses/BSD-3-Clause), copyright the Invisible College. We welcome the technology used in this app.

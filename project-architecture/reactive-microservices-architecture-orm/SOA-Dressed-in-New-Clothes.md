# SOA Dressed in New Clothes?

A valid question to ask is whether Microservices are actually just SOA dressed up in new clothes. The answer is both yes and no. Yes, because the initial goals—decoupling, isolation, composition, inte‐ gration, discrete and autonomous services—are the same. And no, because the fundamental ideas of SOA were most often misunder‐ stood and misused, resulting in complicated systems where an Enterprise Service Bus (ESB) was used to hook up multiple mono‐ liths, communicating over complicated, inefficient and inflexible protocols.

Anne Thomas captures this very well in her article SOA is Dead; Long Live Services:

> Although the word “SOA” is dead, the requirement for service- oriented architecture is stronger than ever. But perhaps that’s the challenge: The acronym got in the way. People forgot what SOA stands for. They were too wrapped up in silly technology debates (e.g., “what’s the best ESB?” or “WS-* vs. REST”), and they missed the important stuff: architecture and services.
Successful SOA (i.e., application re-architecture) requires disrup‐ tion to the status quo. SOA is not simply a matter of deploying new technology and building service interfaces to existing applications; it requires redesign of the application portfolio. And it requires a massive shift in the way IT operates.

As always, new challenges demand a new way of thinking and we have seen new systems emerge that are designed to deal with these new challenges—systems built on the Reactive principles, as defined by the Reactive Manifesto.

The Reactive principles are in no way new. They have been proven and hardened for more than 40 years, going back to the seminal work by Carl Hewitt and his invention of the Actor Model, Jim Gray and Pat Helland at Tandem Systems, and Joe Armstrong and Robert Virding and their work on Erlang. These people were ahead of their time, but now the world has caught up with their innovative think‐ ing and we depend on their discoveries and work more than ever.

What makes Microservices interesting is that this architecture has learned from the failures and successes of SOA, kept the good ideas, and re-architected them from the ground up using Reactive princi‐ ples and modern infrastructure. In sum, Microservices are one of the most interesting applications of the Reactive principles in recent years.

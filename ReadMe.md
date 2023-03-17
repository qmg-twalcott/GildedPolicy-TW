# Gilded Policy Requirements Specification

Hi and welcome to team Gilded Policy. As you know, we are a small insurance company with a wide percentage of customers seeing us on
a comparison website ran by a friendly meerkat named Aleksandr. We sell only the finest policies.
Unfortunately, our policies are constantly degrading in percentage of people viewing them as they approach their expiry date.
We have a system in place that updates our policies for us. It was developed by a no-nonsense type named
Sergei, who has moved on to new adventures. 

Your task is to add the new feature to our system so that we can begin selling a new type of insurance.

First an introduction to our system:

	- All policies have a ExpiryIn value which denotes the number of days we have to sell the policy
	- All policies have a Percentage value which denotes what percentile of customers view our policy
	- At the end of each day our system lowers both values for every policy

Pretty simple, right? Well this is where it gets interesting:

	- Once the expiry date has passed, Percentage degrades twice as fast
	- The Percentage of a policy is never negative (done)
	- "Silver" actually increases in Percentage the older it gets(done)
	- The Percentage of a policy is never more than 50(done)
	- "Gold", being a legendary policy, never has to be sold or decreases in Percentage (done)
	- "Bronze", like silver, increases in Percentage as its ExpiryIn value approaches;
	Percentage increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
	Percentage drops to 0 after the expiry date

We have recently signed a supplier of amazon policies. This requires an update to our system:

	- "Amazon" policies degrade in Percentage twice as fast as normal policies

Feel free to make any changes to the UpdateQuality method and add any new code as long as everything
still works correctly. However, do not alter the Policy class or policies property as those belong to the
Oleg in the corner who is a baby and doesn't believe in shared code
ownership (you can make the UpdateQuality method and policies property static if you like, we'll cover
for you).

Just for clarification, a policy can never have its Percentage increase above 50, however "Gold" is a
legendary policy and as such its Percentage is 80 and it never alters.

## Run the Text Fixture from Command-Line

```
./gradlew -q text
```

### Specify Number of Days

For e.g. 10 days:

```
./gradlew -q text --args 10
```

Feature: Rooms
	As a user I must be able to create and retrieve hotel rooms

@mytag
Scenario: Retrieving non-empty list of hotel rooms
	Given rooms with the following details exists
		| Type     |
		| Suite    |
		| Business |
		| Standard |
		| Basic    |
	When a user request all rooms
	Then A list containing 4 elements is retrieved

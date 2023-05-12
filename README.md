# alea-iacta-sys-sigmata
A RPG system module for Alea Iacta Est implementing "Sigmata: This Signal Kills Fascists"

## Description
This command will roll 5 dice, divided between Processor Dice (d10) and Complementary Dice (d6), and use the results to generate a success value given that every result of 1 subtracts a success and every result of 6 or more add a success.

### Roll modifiers
Passing these parameters, the associated modifier will be enabled:

* `-v` : Will enable a more verbose mode that will show a detailed version of every result obtained in the roll.
* `-o` : Will disable the auto-critical on natual 1 in simple mode, to support older games like Conan & Mutant Chonicles.

## Help print
```
Stigmata: This Signal Kills Fascists [ stigmata | stg ]

Usage: stg [-v] -p <targetNumber>
Description:
This command will roll 5 dice divided between 10-sided dice
and 6-sided dice, to generate a success value between -5
and 5, that is used to describe the outcomes.

Options:
  -p, --processor=tacticValue
                  Processor/Tactic value to use for the roll
  -h, --help      Print the command help
  -v, --verbose   Enable verbose output
```

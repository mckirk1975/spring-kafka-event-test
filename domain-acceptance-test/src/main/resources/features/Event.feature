Feature: Event

  Scenario: Process INPUT Event

    Given "INPUT" event
    When event is published to topic "input.topic"
    Then "OUTPUT" event is published to topic "output.topic"
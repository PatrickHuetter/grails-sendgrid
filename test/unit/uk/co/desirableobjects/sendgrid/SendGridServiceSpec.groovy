package uk.co.desirableobjects.sendgrid

import grails.plugin.spock.UnitSpec
import spock.lang.Shared

class SendGridServiceSpec extends UnitSpec {

    private static final String RECIPIENT = 'recipient@example.org'
    private static final String SENDER = 'sender@example.org'
    private static final String MESSAGE_TEXT = 'This is a test'
    private static final String SUBJECT = 'Hello there'

    @Shared SendGridService sendGridService
    @Shared SendGridApiConnectorService api

    def setupSpec() {
        mockConfig ''
        api = new MockSendGridConnector()
    }

    def 'Send an email'() {

        given:
            sendGridService = new SendGridService()
            sendGridService.sendGridApiConnectorService = api

        when:
            sendGridService.send(to: RECIPIENT, subject:SUBJECT, text:MESSAGE_TEXT, from:SENDER)

        then:
            api.method == 'post'
            api.to == RECIPIENT
            api.subject == SUBJECT
            api.text == MESSAGE_TEXT
            api.from == SENDER

    }

}

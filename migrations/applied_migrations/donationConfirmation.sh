#!/bin/bash

echo "Applying migration donationConfirmation"

echo "Adding routes to conf/app.routes"
echo "" >> ../conf/app.routes
echo "GET        /donationConfirmation                       controllers.donationConfirmationController.onPageLoad()" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "donationConfirmation.title = donationConfirmation" >> ../conf/messages.en
echo "donationConfirmation.heading = donationConfirmation" >> ../conf/messages.en

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration donationConfirmation completed"

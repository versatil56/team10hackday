#!/bin/bash

echo "Applying migration giftAidAmount"

echo "Adding routes to conf/app.routes"
echo "" >> ../conf/app.routes
echo "GET        /giftAidAmount                       controllers.giftAidAmountController.onPageLoad()" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "giftAidAmount.title = giftAidAmount" >> ../conf/messages.en
echo "giftAidAmount.heading = giftAidAmount" >> ../conf/messages.en

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration giftAidAmount completed"

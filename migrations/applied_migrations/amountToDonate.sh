#!/bin/bash

echo "Applying migration amountToDonate"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /amountToDonate               controllers.amountToDonateController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /amountToDonate               controllers.amountToDonateController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeamountToDonate                        controllers.amountToDonateController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeamountToDonate                        controllers.amountToDonateController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "amountToDonate.title = amountToDonate" >> ../conf/messages.en
echo "amountToDonate.heading = amountToDonate" >> ../conf/messages.en
echo "amountToDonate.checkYourAnswersLabel = amountToDonate" >> ../conf/messages.en
echo "amountToDonate.error.nonNumeric = Please give an answer for amountToDonate using numbers" >> ../conf/messages.en
echo "amountToDonate.error.required = Please give an answer for amountToDonate" >> ../conf/messages.en
echo "amountToDonate.error.wholeNumber = Please give an answer for amountToDonate using whole numbers" >> ../conf/messages.en
echo "amountToDonate.error.outOfRange = amountToDonate must be between {0} and {1}" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def amountToDonate: Option[Int] = cacheMap.getEntry[Int](amountToDonateId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def amountToDonate: Option[AnswerRow] = userAnswers.amountToDonate map {";\
     print "    x => AnswerRow(\"amountToDonate.checkYourAnswersLabel\", s\"$x\", false, routes.amountToDonateController.onPageLoad(CheckMode).url)";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration amountToDonate completed"

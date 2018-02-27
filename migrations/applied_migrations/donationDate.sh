#!/bin/bash

echo "Applying migration donationDate"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /donationDate               controllers.donationDateController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /donationDate               controllers.donationDateController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changedonationDate                        controllers.donationDateController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changedonationDate                        controllers.donationDateController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "donationDate.title = donationDate" >> ../conf/messages.en
echo "donationDate.heading = donationDate" >> ../conf/messages.en
echo "donationDate.checkYourAnswersLabel = donationDate" >> ../conf/messages.en
echo "donationDate.error.required = Enter donationDate" >> ../conf/messages.en
echo "donationDate.error.length = donationDate must be 20 characters or less" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def donationDate: Option[String] = cacheMap.getEntry[String](donationDateId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def donationDate: Option[AnswerRow] = userAnswers.donationDate map {";\
     print "    x => AnswerRow(\"donationDate.checkYourAnswersLabel\", s\"$x\", false, routes.donationDateController.onPageLoad(CheckMode).url)";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration donationDate completed"

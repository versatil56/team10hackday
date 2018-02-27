#!/bin/bash

echo "Applying migration donatorsPostcode"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /donatorsPostcode               controllers.donatorsPostcodeController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /donatorsPostcode               controllers.donatorsPostcodeController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changedonatorsPostcode                        controllers.donatorsPostcodeController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changedonatorsPostcode                        controllers.donatorsPostcodeController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "donatorsPostcode.title = donatorsPostcode" >> ../conf/messages.en
echo "donatorsPostcode.heading = donatorsPostcode" >> ../conf/messages.en
echo "donatorsPostcode.checkYourAnswersLabel = donatorsPostcode" >> ../conf/messages.en
echo "donatorsPostcode.error.required = Enter donatorsPostcode" >> ../conf/messages.en
echo "donatorsPostcode.error.length = donatorsPostcode must be 100 characters or less" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def donatorsPostcode: Option[String] = cacheMap.getEntry[String](donatorsPostcodeId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def donatorsPostcode: Option[AnswerRow] = userAnswers.donatorsPostcode map {";\
     print "    x => AnswerRow(\"donatorsPostcode.checkYourAnswersLabel\", s\"$x\", false, routes.donatorsPostcodeController.onPageLoad(CheckMode).url)";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration donatorsPostcode completed"

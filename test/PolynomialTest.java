import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * JUnit class for testing implementation of Polynomial interface and PolynomialImpl class.
 */
public class PolynomialTest {

  private final String failMsg = "Expected execution to terminate with exception. Got "
          + "successful execution instead";
  private final double delta = 0.00001;
  private final String input1 =
          "+98x^194 +35x^189 +50x^187 -62x^186 +43x^181 -96x^180 +69x^175 +16x^174 +70x^172 "
                  + "-141x^168 +45x^165 -9x^163 +188x^159 +10x^158 +59x^157 +67x^156 +98x^154 "
                  + "+12x^153 "
                  + "-18x^152 +104x^151 +21x^145 -39x^144 -140x^142 +80x^141 +17x^139 -76x^138 "
                  + "+83x^137"
                  + " +59x^136 -44x^135 +83x^131 -27x^130 +32x^127 +97x^126 +41x^125 -55x^123 "
                  + "-50x^121 "
                  + "-29x^119 +56x^118 +36x^117 -30x^111 +44x^108 +6x^107 +70x^104 +9x^101 +6x^99 "
                  + "+190x^95 +55x^93 -43x^92 +75x^91 -54x^90 +93x^88 +86x^87 +87x^86 -12x^85 "
                  + "-35x^82 "
                  + "-219x^80 +73x^79 +82x^77 -80x^73 +96x^70 +65x^60 +81x^58 -104x^54 -118x^52 "
                  + "-174x^50 +84x^47 +68x^46 -62x^40 +59x^39 -56x^38 +66x^31 +84x^24 -1x^23 "
                  + "-49x^22 "
                  + "+35x^21 -99x^16 +65x^15 +34x^14 -65x^9 -85x^8 +40x^2";

  private final String input2 =
          "-56x^198 +81x^197 +73x^193 -16x^192 +86x^190 +162x^189 -94x^183 +122x^182 -17x^180"
                  + " -50x^179 -14x^174 +16x^173 -344x^169 +54x^165 -13x^163 +180x^159 -23x^156 "
                  + "-42x^155 -27x^152 +8x^148 +39x^144 -64x^143 -114x^142 +92x^141 -49x^140 "
                  + "+27x^139 "
                  + "+39x^138 +55x^135 +5x^133 -85x^132 -94x^131 -92x^130 -12x^127 -78x^126 "
                  + "-176x^119 "
                  + "+15x^114 +13x^112 -2x^109 -48x^108 -130x^106 +20x^100 +1x^98 -43x^90 +7x^86 "
                  + "-2x^84"
                  + " -88x^83 +54x^82 +7x^81 -45x^79 +170x^78 -52x^70 -194x^66 +80x^65 -132x^64 "
                  + "+14x^63"
                  + " +33x^62 +31x^59 -11x^56 +17x^54 -25x^53 +4x^47 -46x^41 -51x^40 -57x^38 "
                  + "-66x^34 "
                  + "+30x^28 -70x^26 -62x^21 +28x^17 -15x^15 -34x^14 -89x^11 +62x^10 -39x^8 +71x^5 "
                  + "+98x^4 +54x^3 +17x^2 +98x^1 +53";

  private final String product = "-5488x^392+7938x^391+5194x^387+1267x^386-2800x^385+15950x^384"
          + "+10854x^383+2555x^382-560x^381+3650x^380-4724x^379+15521x^378-12688x^377+14724x^376"
          + "-10044x^375+1473x^374-16460x^373+2939x^372+9264x^371-9910x^370+1451x^369-5649x^368"
          + "+782x^367+6194x^366+2723x^365+7392x^364-19860x^363-1487x^362+120x^361+2677x^360"
          + "+9100x^359-27367x^358-28450x^357-1237x^356+14493x^355+17057x^354+19957x^353+7833x"
          + "^352+11394x^351-28771x^350+41581x^349+44015x^348+26644x^347+23170x^346-8544x^345"
          + "-11707x^344+8815x^343-10857x^342+188x^341+31828x^340-36512x^339+11936x^338+30371x"
          + "^337+2082x^336-6874x^335-10449x^334+2713x^333-4519x^332-12348x^331+11807x^330+2480x"
          + "^329-58557x^328-38951x^327-10347x^326-15266x^325-23632x^324-11484x^323+17482x^322"
          + "+15221x^321-50954x^320+7616x^319+37949x^318-9206x^317+30688x^316+10892x^315-22402x"
          + "^314+23159x^313-14195x^312+49403x^311+8676x^310-22122x^309-3888x^308+22160x^307"
          + "-34544x^306-26288x^305+15433x^304-23527x^303-17256x^302-48707x^301-10793x^300+26908x"
          + "^299+25083x^298-865x^297-14552x^296-14795x^295-1745x^294-21889x^293+55224x^292"
          + "-35512x^291+35820x^290-26243x^289+18555x^288-27111x^287-2589x^286+17123x^285+35769x"
          + "^284-33973x^283-12499x^282-18804x^281+1809x^280-28956x^279-41201x^278-4492x^277"
          + "+29474x^276+17378x^275+14833x^274-53905x^273+29214x^272-21847x^271-34788x^270-20906x"
          + "^269+4283x^268-4242x^267+10236x^266-9252x^265-87864x^264+36750x^263-99230x^262"
          + "+54734x^261-67095x^260+65378x^259-51030x^258-51143x^257-47532x^256-58895x^255+55425x"
          + "^254+11396x^253+54191x^252+673x^251+20917x^250+53285x^249-1129x^248-16765x^247"
          + "-25936x^246-39771x^245-8611x^244-29185x^243+10637x^242-35305x^241+2223x^240-109602x"
          + "^239-14403x^238+1701x^237+37107x^236+13645x^235+6300x^234-5069x^233+16705x^232"
          + "-22845x^231-20231x^230+4493x^229+6534x^228-56002x^227-25577x^226-45298x^225-4144x"
          + "^224+4287x^223-8260x^222+34533x^221-48194x^220+45391x^219-7172x^218-20562x^217"
          + "-36641x^216-57400x^215-23964x^214-20486x^213-357x^212+3204x^211-13728x^210-10051x"
          + "^209+14598x^208-30261x^207+56831x^206-29720x^205+58328x^204-30525x^203-5036x^202"
          + "-53749x^201-57206x^200+37548x^199-38312x^198-55906x^197+1452x^196+4670x^195+2366x"
          + "^194-49152x^193+372x^192+2543x^191-32956x^190-42798x^189+5532x^188-801x^187+28083x"
          + "^186+6731x^185-20738x^184-6583x^183+53075x^182-16880x^181+30759x^180+4133x^179"
          + "-12945x^178+8976x^177+14141x^176-9126x^175-22097x^174+34277x^173-11991x^172-8447x"
          + "^171-34176x^170+46903x^169-18738x^168-47626x^167-25009x^166-18804x^165+28683x^164"
          + "+35132x^163-14589x^162-10622x^161+42167x^160+25848x^159+5448x^158-20230x^157+61086x"
          + "^156+3707x^155-6525x^154-7866x^153-40644x^152-9816x^151-8038x^150+3524x^149+18358x"
          + "^148-26019x^147+58478x^146-54740x^145+26912x^144-57677x^143+3850x^142+9321x^141"
          + "+13453x^140+2426x^139+8689x^138+12315x^137-10468x^136+37709x^135-32141x^134+420x^133"
          + "-20839x^132+11905x^131-24875x^130-12011x^129-16682x^128+6899x^127-20589x^126+217x"
          + "^125-10708x^124+10848x^123-4021x^122-13607x^121+25848x^120-7636x^119+40218x^118"
          + "-7380x^117+28116x^116-17758x^115+35473x^114-21951x^113-15387x^112-12064x^111-14452x"
          + "^110+1150x^109-6594x^108-1147x^107+24764x^106+8693x^105+11250x^104-13904x^103+7178x"
          + "^102+13082x^101-10433x^100+22271x^99-1307x^98-17862x^97+25686x^96+11047x^95-9251x^94"
          + "+24897x^93+32644x^92+40615x^91-13608x^90+24943x^89+33042x^88-6973x^87+5085x^86"
          + "-38842x^85-13465x^84-9023x^83+20241x^82-48180x^81+24260x^80-4047x^79+7848x^78-4918x"
          + "^77+11366x^76+27078x^75+17920x^74+105x^73+4097x^72+4419x^71+4252x^70-9331x^69-11727x"
          + "^68-5833x^67-1767x^66+5406x^65+10127x^64+22471x^63+1064x^62+27971x^61-4541x^60+4011x"
          + "^59-12573x^58-16574x^57-1751x^56-41361x^55-22090x^54-25831x^53-4854x^52+1496x^51"
          + "-4943x^50+17957x^49+15703x^48+10070x^47+9643x^46-11854x^45-4795x^44+5896x^43+120x^42"
          + "-6203x^41-3144x^40-7567x^39-4829x^38+4957x^37-3393x^36+244x^35+14811x^34+2649x^33"
          + "-1141x^32+8144x^31+9532x^30+7149x^29+4205x^28+9770x^27-14686x^26+7623x^25+12355x^24"
          + "-5790x^23+2397x^22-5174x^21+698x^20+8093x^19-111x^18-4826x^17+3656x^16+6777x^15"
          + "-2813x^14-15965x^13-9360x^12-5695x^11-9375x^10-11775x^9-4505x^8+2840x^7+3920x^6"
          + "+2160x^5+680x^4+3920x^3+2120x^2";

  private final String sum = "-56x^198+81x^197+98x^194+73x^193-16x^192+86x^190+197x^189+50x^187"
          + "-62x^186-94x^183+122x^182+43x^181-113x^180-50x^179+69x^175+2x^174+16x^173+70x^172"
          + "-344x^169-141x^168+99x^165-22x^163+368x^159+10x^158+59x^157+44x^156-42x^155+98x^154"
          + "+12x^153-45x^152+104x^151+8x^148+21x^145-64x^143-254x^142+172x^141-49x^140+44x^139"
          + "-37x^138+83x^137+59x^136+11x^135+5x^133-85x^132-11x^131-119x^130+20x^127+19x^126+41x"
          + "^125-55x^123-50x^121-205x^119+56x^118+36x^117+15x^114+13x^112-30x^111-2x^109-4x^108"
          + "+6x^107-130x^106+70x^104+9x^101+20x^100+6x^99+1x^98+190x^95+55x^93-43x^92+75x^91-97x"
          + "^90+93x^88+86x^87+94x^86-12x^85-2x^84-88x^83+19x^82+7x^81-219x^80+28x^79+170x^78+82x"
          + "^77-80x^73+44x^70-194x^66+80x^65-132x^64+14x^63+33x^62+65x^60+31x^59+81x^58-11x^56"
          + "-87x^54-25x^53-118x^52-174x^50+88x^47+68x^46-46x^41-113x^40+59x^39-113x^38-66x^34"
          + "+66x^31+30x^28-70x^26+84x^24-1x^23-49x^22-27x^21+28x^17-99x^16+50x^15-89x^11+62x^10"
          + "-65x^9-124x^8+71x^5+98x^4+54x^3+57x^2+98x^1+53";

  private final String derivative1
          = "19012x^193+6615x^188+9350x^186-11532x^185+7783x^180-17280x^179+12075x^174+2784x^173"
          + "+12040x^171-23688x^167+7425x^164-1467x^162+29892x^158+1580x^157+9263x^156+10452x^155"
          + "+15092x^153+1836x^152-2736x^151+15704x^150+3045x^144-5616x^143-19880x^141+11280x^140"
          + "+2363x^138-10488x^137+11371x^136+8024x^135-5940x^134+10873x^130-3510x^129+4064x^126"
          + "+12222x^125+5125x^124-6765x^122-6050x^120-3451x^118+6608x^117+4212x^116-3330x^110"
          + "+4752x^107+642x^106+7280x^103+909x^100+594x^98+18050x^94+5115x^92-3956x^91+6825x^90"
          + "-4860x^89+8184x^87+7482x^86+7482x^85-1020x^84-2870x^81-17520x^79+5767x^78+6314x^76"
          + "-5840x^72+6720x^69+3900x^59+4698x^57-5616x^53-6136x^51-8700x^49+3948x^46+3128x^45"
          + "-2480x^39+2301x^38-2128x^37+2046x^30+2016x^23-23x^22-1078x^21+735x^20-1584x^15+975x"
          + "^14+476x^13-585x^8-680x^7+80x^1";

  /**
   * Test a large invalid polynomial input to the string constructor.
   */
  @Test
  public void testCase1() {
    try {
      new PolynomialImpl("+20x^10 +20x^9 +16x^9 +20x^8 +16x^8 +12x^8 +20x^7 "
              + "+16x^7 +12x^7 +8x^7 +20x^6 +16x^6 +12x^6 +8x^6 +4x^6 +16x^5 +12x^5 +8x^5"
              + " +4x^5 +12x^4 +8x^4 +4x^4 +8x ^3 +4x^3 +4x^2");

      fail(failMsg);
    } catch (Exception ignored) {
      ignored.printStackTrace();
    }
  }

  /**
   * Test a large valid string with jumbled terms in the constructor.
   */
  @Test
  public void testCase2() {
    String input = "+20x^10 +20x^9 +16x^9 +20x^8 +16x^8 +12x^8 +20x^7 "
            + "+16x^7 +12x^7 +8x^7 +20x^6 +16x^6 +12x^6 +8x^6 +4x^6 +16x^5 +12x^5 +8x^5"
            + " +4x^5 +12x^4 +8x^4 +4x^4 +8x^3 +4x^3 +4x^2";
    String orderedOutput = "20x^10+36x^9+48x^8+56x^7+60x^6+40x^5+24x^4+12x^3+4x^2";
    Polynomial polynomial = new PolynomialImpl(input);

    assertEquals(orderedOutput, polynomial.toString());
  }

  /**
   * Test the characteristics of an empty polynomial made using the no-arg constructor.
   */
  @Test
  public void testCase3() {
    Polynomial polynomial = new PolynomialImpl();
    assertEquals(0, polynomial.getCoefficient(0));
    assertEquals("0", polynomial.toString());

    // check equality with another empty polynomial.
    assertEquals(polynomial, new PolynomialImpl());

    // identity check.
    assertEquals(polynomial, polynomial);

    // evaluate against a variety of inputs.
    assertEquals(0, polynomial.evaluate(1), delta);
    assertEquals(0, polynomial.evaluate(0), delta);
    assertEquals(0, polynomial.evaluate(1000), delta);

    Polynomial polynomial2 = new PolynomialImpl("2x^1");

    // check target polynomial.
    assertEquals("2x^1", polynomial2.toString());

    // result of multiplying something with 0 is 0.
    assertEquals("0", polynomial.multiply(polynomial2).toString());
    assertEquals(polynomial, polynomial.multiply(polynomial2));

    // result of addition of something to 0 is the thing itself.
    assertEquals(polynomial2, polynomial.add(polynomial2));

    // derivative of 0 is 0.
    assertEquals(polynomial, polynomial.derivative());

    try {
      assertEquals(polynomial.getDegree(), 0);
      fail(failMsg);
    } catch (Exception ignored) {
      ignored.printStackTrace();
    }
  }

  /**
   * Test the constructor which takes string as the argument.
   */
  @Test
  public void testCase4() {
    Polynomial polynomial = new PolynomialImpl("3x^2 +2x^1 +5");

    assertEquals(2, polynomial.getDegree());

    assertEquals(3, polynomial.getCoefficient(2));
    assertEquals(2, polynomial.getCoefficient(1));
    assertEquals(5, polynomial.getCoefficient(0));
    // check non-existent power.
    assertEquals(0, polynomial.getCoefficient(6));

    assertEquals("3x^2+2x^1+5", polynomial.toString());
    assertEquals(10, polynomial.evaluate(1), delta);
    assertEquals(5, polynomial.evaluate(0), delta);

    // test polynomial with just the constant.
    Polynomial polynomial2 = new PolynomialImpl("2");
    assertEquals(0, polynomial2.getDegree());
    assertEquals(2, polynomial2.getCoefficient(0));
    assertEquals(0, polynomial2.getCoefficient(2));
    assertEquals(2, polynomial2.evaluate(1), delta);
    assertEquals(2, polynomial2.evaluate(0), delta);
    assertEquals(2, polynomial2.evaluate(1000000), delta);

    // test large valid string as input.
    Polynomial polynomial3 = new PolynomialImpl(input1);
    assertEquals(194, polynomial3.getDegree());
    assertEquals(98, polynomial3.getCoefficient(194));
    assertEquals(input1.replaceAll(" ", "").substring(1), polynomial3.toString());
    assertEquals(188, polynomial3.getCoefficient(159));
    assertEquals(0, polynomial3.getCoefficient(900));
  }

  /**
   * Test the string constructor with invalid strings.
   */
  @Test
  public void testCase5() {
    try {
      new PolynomialImpl("3x^2+2x+5");
      fail(failMsg);
    } catch (IllegalArgumentException ignored) {
      ignored.printStackTrace();
    }

    // too many spaces.
    try {
      new PolynomialImpl("3x^2+   2x+5");
      fail(failMsg);
    } catch (IllegalArgumentException ignored) {
      ignored.printStackTrace();
    }

    // missing exponent sign.
    try {
      new PolynomialImpl("3x^2+2x+5");
      fail(failMsg);
    } catch (IllegalArgumentException ignored) {
      ignored.printStackTrace();
    }

    // no sign between terms
    try {
      new PolynomialImpl("3x^2 2x^1 +5");
      fail(failMsg);
    } catch (IllegalArgumentException ignored) {
      ignored.printStackTrace();
    }

    // missing constant.
    try {
      new PolynomialImpl("x^2+2x+5");
      fail(failMsg);
    } catch (IllegalArgumentException ignored) {
      ignored.printStackTrace();
    }

    // leading spaces.
    try {
      new PolynomialImpl("      x^2+2x+5");
      fail(failMsg);
    } catch (IllegalArgumentException ignored) {
      ignored.printStackTrace();
    }

    // trailing spaces.
    try {
      new PolynomialImpl("x^2 +2x^1 +5         ");
      fail(failMsg);
    } catch (IllegalArgumentException ignored) {
      ignored.printStackTrace();
    }
  }

  /**
   * Test the addTerm method in a multiple ways.
   */
  @Test
  public void testCase6() {
    Polynomial polynomial = new PolynomialImpl();
    assertEquals("0", polynomial.toString());

    polynomial.addTerm(5, 9); // 5x^9
    assertEquals("5x^9", polynomial.toString());
    assertEquals(9, polynomial.getDegree());
    assertEquals(5, polynomial.getCoefficient(9));
    assertEquals(2560, polynomial.evaluate(2), delta);

    // add constant.
    polynomial.addTerm(8, 0);
    assertEquals("5x^9+8", polynomial.toString());
    assertEquals(9, polynomial.getDegree());
    assertEquals(5, polynomial.getCoefficient(9));
    assertEquals(8, polynomial.getCoefficient(0));
    assertEquals(2568, polynomial.evaluate(2), delta);

    // add term with zero coefficient. Should not add anything.
    polynomial.addTerm(0, 5);
    assertEquals("5x^9+8", polynomial.toString());
    assertEquals(9, polynomial.getDegree());
    assertEquals(5, polynomial.getCoefficient(9));
    assertEquals(8, polynomial.getCoefficient(0));
    assertEquals(2568, polynomial.evaluate(2), delta);

    // add term with negative coefficient.
    polynomial.addTerm(-10, 5);
    assertEquals("5x^9-10x^5+8", polynomial.toString());
    assertEquals(9, polynomial.getDegree());
    assertEquals(5, polynomial.getCoefficient(9));
    assertEquals(8, polynomial.getCoefficient(0));
    assertEquals(3, polynomial.evaluate(1), delta);

    // add term with existing power in the polynomial.
    polynomial.addTerm(4, 9);
    assertEquals("9x^9-10x^5+8", polynomial.toString());
    assertEquals(9, polynomial.getDegree());
    assertEquals(9, polynomial.getCoefficient(9));
    assertEquals(8, polynomial.getCoefficient(0));
    assertEquals(7, polynomial.evaluate(1), delta);

    // add term with negative coefficient of existing power.
    polynomial.addTerm(-9, 9);
    assertEquals("-10x^5+8", polynomial.toString());
    assertEquals(5, polynomial.getDegree());
    assertEquals(0, polynomial.getCoefficient(9));
    assertEquals(8, polynomial.getCoefficient(0));
    assertEquals(-2, polynomial.evaluate(1), delta);

    // add invalid term. should throw exception.
    try {
      polynomial.addTerm(-5, -4);
      fail(failMsg);
    } catch (IllegalArgumentException ignored) {
      ignored.printStackTrace();
    }
  }

  /**
   * Test the getDegree method of the polynomial.
   */
  @Test
  public void testCase7() {
    Polynomial polynomial = new PolynomialImpl(input1);
    assertEquals(194, polynomial.getDegree());

    Polynomial polynomial1 = new PolynomialImpl(input2);
    assertEquals(198, polynomial1.getDegree());

    Polynomial polynomial2 = polynomial.multiply(polynomial1);
    assertEquals(392, polynomial2.getDegree());

    Polynomial polynomial3 = polynomial.add(polynomial1);
    assertEquals(198, polynomial3.getDegree());

    assertEquals(193, polynomial.derivative().getDegree());
    assertEquals(197, polynomial1.derivative().getDegree());
    assertEquals(391, polynomial2.derivative().getDegree());
    assertEquals(197, polynomial3.derivative().getDegree());
  }

  /**
   * Test the getCoefficient method of the polynomial.
   */
  @Test
  public void testCase8() {
    Polynomial polynomial = new PolynomialImpl(input1);
    assertEquals(98, polynomial.getCoefficient(194));
    assertEquals(45, polynomial.getCoefficient(165));
    assertEquals(-140, polynomial.getCoefficient(142));
    assertEquals(17, polynomial.getCoefficient(139));
    assertEquals(-76, polynomial.getCoefficient(138));

    // test non existing powers.
    assertEquals(0, polynomial.getCoefficient(300));
    assertEquals(0, polynomial.getCoefficient(0));
    assertEquals(0, polynomial.getCoefficient(-100));
  }

  /**
   * Test the equality of polynomials.
   */
  @Test
  public void testCase9() {
    Polynomial polynomial1 = new PolynomialImpl("1x^2 +6x^1 +9");
    Polynomial polynomial2 = new PolynomialImpl("+9 +1x^2 +6x^1");

    assertEquals(polynomial1, polynomial2);

    Polynomial polynomial3 = new PolynomialImpl("1x^2 +6x^1 +9 +18x^3 +29x^4 +100x^5 +200x^5");
    Polynomial polynomial4 = new PolynomialImpl("300x^5 +9 +1x^2 +6x^1 +18x^3 +29x^4");

    assertEquals(polynomial3, polynomial4);

    assertNotEquals(polynomial1, polynomial3);
    assertNotEquals(polynomial1, polynomial4);
    assertNotEquals(polynomial2, polynomial3);
    assertNotEquals(polynomial2, polynomial4);

    // identity
    assertEquals(polynomial1, polynomial1);

    // order does not matter.
    assertEquals(polynomial2, polynomial1);
    assertEquals(polynomial4, polynomial3);
  }

  /**
   * Test the multiply functionality of the polynomial.
   */
  @Test
  public void testCase10() {
    Polynomial polynomial = new PolynomialImpl("2x^2 +5");
    Polynomial polynomial1 = new PolynomialImpl("8");

    Polynomial originalPolynomial = polynomial;
    Polynomial originalPolynomial1 = polynomial1;

    Polynomial polynomial2 = polynomial.multiply(polynomial1);
    assertEquals(2, polynomial2.getDegree());
    assertEquals(16, polynomial2.getCoefficient(2));
    assertEquals(40, polynomial2.getCoefficient(0));
    assertEquals("16x^2+40", polynomial2.toString());
    assertEquals(56, polynomial2.evaluate(1), delta);
    assertEquals(40, polynomial2.evaluate(0), delta);

    // check to make sure that original Polynomials are not mutated.
    assertEquals(polynomial, originalPolynomial);
    assertEquals(polynomial1, originalPolynomial1);

    Polynomial polynomial3 = polynomial2.multiply(new PolynomialImpl("-2x^3"));
    assertEquals(5, polynomial3.getDegree());
    assertEquals(0, polynomial3.getCoefficient(2));
    assertEquals(-32, polynomial3.getCoefficient(5));
    assertEquals("-32x^5-80x^3", polynomial3.toString());
    assertEquals(-112, polynomial3.evaluate(1), delta);
    assertEquals(0, polynomial3.evaluate(0), delta);

    Polynomial polynomial4 = polynomial3.multiply(new PolynomialImpl());
    assertEquals(0, polynomial4.getCoefficient(2));
    assertEquals(0, polynomial4.getCoefficient(5));
    assertEquals("0", polynomial4.toString());
    assertEquals(0, polynomial4.evaluate(1), delta);
    assertEquals(0, polynomial4.evaluate(0), delta);

    try {
      assertEquals(0, polynomial4.getDegree());
      fail(failMsg);
    } catch (Exception ignored) {
      ignored.printStackTrace();
    }
  }

  /**
   * Test the multiply functionality of the polynomial.
   */
  @Test
  public void testCase11() {
    // test multiplication of large polynomials.
    Polynomial polynomial = new PolynomialImpl(input1);
    Polynomial polynomial1 = new PolynomialImpl(input2);

    Polynomial originalPolynomial = polynomial;
    Polynomial originalPolynomial1 = polynomial1;

    Polynomial polynomial2 = polynomial.multiply(polynomial1);
    assertEquals(392, polynomial2.getDegree());
    assertEquals(product, polynomial2.toString());

    // check to make sure that original Polynomials are not mutated.
    assertEquals(polynomial, originalPolynomial);
    assertEquals(polynomial1, originalPolynomial1);
  }

  /**
   * Test the addition functionality of polynomials.
   */
  @Test
  public void testCase12() {
    Polynomial polynomial = new PolynomialImpl();
    Polynomial polynomial1 = new PolynomialImpl();

    assertEquals(0, polynomial.add(polynomial1).getCoefficient(0));
    assertEquals(0, polynomial.add(polynomial1).getCoefficient(100));

    Polynomial polynomial2 = new PolynomialImpl(input1);
    Polynomial polynomial3 = new PolynomialImpl(input2);

    Polynomial originalPolynomial = polynomial2;
    Polynomial originalPolynomial1 = polynomial3;

    Polynomial polynomial4 = polynomial2.add(polynomial3);
    assertEquals(sum, polynomial4.toString());
    assertEquals(198, polynomial4.getDegree());

    // test immutability
    assertEquals(polynomial2, originalPolynomial);
    assertEquals(polynomial3, originalPolynomial1);
  }

  /**
   * Test the addition functionality of polynomials.
   */
  @Test
  public void testCase13() {
    Polynomial polynomial = new PolynomialImpl("2x^2 +5");
    Polynomial polynomial1 = new PolynomialImpl("8");

    Polynomial originalPolynomial = polynomial;
    Polynomial originalPolynomial1 = polynomial1;

    Polynomial polynomial2 = polynomial.add(polynomial1);
    assertEquals(2, polynomial2.getDegree());
    assertEquals(2, polynomial2.getCoefficient(2));
    assertEquals(13, polynomial2.getCoefficient(0));
    assertEquals("2x^2+13", polynomial2.toString());
    assertEquals(15, polynomial2.evaluate(1), delta);
    assertEquals(13, polynomial2.evaluate(0), delta);

    // check to make sure that original Polynomials are not mutated.
    assertEquals(polynomial, originalPolynomial);
    assertEquals(polynomial1, originalPolynomial1);

    Polynomial polynomial3 = polynomial2.add(new PolynomialImpl("-2x^2"));
    assertEquals(0, polynomial3.getDegree());
    assertEquals(0, polynomial3.getCoefficient(2));
    assertEquals(0, polynomial3.getCoefficient(5));
    assertEquals("13", polynomial3.toString());
    assertEquals(13, polynomial3.evaluate(1), delta);
    assertEquals(13, polynomial3.evaluate(0), delta);

    // multiply polynomial with negative of itself.
    Polynomial polynomial4 = polynomial3.add(polynomial3.multiply(new PolynomialImpl("-1")));
    assertEquals(0, polynomial4.getCoefficient(2));
    assertEquals(0, polynomial4.getCoefficient(5));
    assertEquals("0", polynomial4.toString());
    assertEquals(0, polynomial4.evaluate(1), delta);
    assertEquals(0, polynomial4.evaluate(0), delta);

    try {
      assertEquals(0, polynomial4.getDegree());
      fail(failMsg);
    } catch (Exception ignored) {
      ignored.printStackTrace();
    }
  }

  /**
   * Test the derivative functionality of the polynomial.
   */
  @Test
  public void testCase14() {
    {
      Polynomial polynomial = new PolynomialImpl("2x^2 +5");

      Polynomial originalPolynomial = polynomial;

      Polynomial polynomial2 = polynomial.derivative();
      assertEquals(1, polynomial2.getDegree());
      assertEquals(0, polynomial2.getCoefficient(2));
      assertEquals(4, polynomial2.getCoefficient(1));
      assertEquals("4x^1", polynomial2.toString());
      assertEquals(4, polynomial2.evaluate(1), delta);
      assertEquals(0, polynomial2.evaluate(0), delta);

      // check to make sure that original Polynomials are not mutated.
      assertEquals(polynomial, originalPolynomial);

      Polynomial polynomial3 = new PolynomialImpl("1x^3 +1x^2 +1x^1 +1");
      assertEquals(3, polynomial3.getDegree());
      assertEquals(1, polynomial3.getCoefficient(2));
      assertEquals(0, polynomial3.getCoefficient(5));
      assertEquals("1x^3+1x^2+1x^1+1", polynomial3.toString());
      assertEquals(4, polynomial3.evaluate(1), delta);
      assertEquals(1, polynomial3.evaluate(0), delta);

      // multiply polynomial with negative of itself.
      Polynomial polynomial4 = polynomial3.derivative();
      assertEquals(3, polynomial4.getCoefficient(2));
      assertEquals(2, polynomial4.getCoefficient(1));
      assertEquals("3x^2+2x^1+1", polynomial4.toString());
      assertEquals(6, polynomial4.evaluate(1), delta);
      assertEquals(1, polynomial4.evaluate(0), delta);
    }
  }

  /**
   * Test the derivative functionality of polynomial.
   */
  @Test
  public void testCase15() {
    Polynomial polynomial = new PolynomialImpl(input1);
    Polynomial polynomial1 = polynomial.derivative();

    System.out.println(polynomial1);
    Polynomial originalPolynomial = polynomial;

    assertEquals(derivative1, polynomial1.toString());
    assertEquals(polynomial, originalPolynomial);
  }
}

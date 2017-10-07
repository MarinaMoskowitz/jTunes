/*
package cs3500.hw01.duration;

import org.junit.Test;

import CompactDuration;
import Duration;
import HmsDuration;

import static org.junit.Assert.assertEquals;

*/
/** Tests for the format method of {@link Duration}s.
    Add your tests to this class to assure that your format 
    method works properly
*//*

public abstract class AbstractDurationFormatTest {
  @Test
  public void formatExample1() {
    assertEquals("4 hours, 0 minutes, and 9 seconds",
                  hms(4, 0, 9)
                    .format("%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void formatExample2() {
    assertEquals("4:05:17",
                  hms(4, 5, 17).format("%h:%M:%S"));
    assertEquals("04:05:17",
                  hms(4, 5, 17).format("%H:%M:%S"));
  }

  // ADD MORE TESTS HERE
  // Your tests must only use hms(...) and sec(...) to construct new Durations
  // and must *not* directly say "new CompactDuration(...)" or
  // "new HmsDuration(...)"

  Duration d1;
  Duration d2;
  Duration d3;
  Duration d4;
  Duration d5;
  Duration d6;
  Duration d7;
  Duration d8;
  Duration d9;
  Duration d10;
  Duration d11;
  Duration d12;
  Duration d13;
  Duration d14;
  Duration d15;

  private void initialize() {

    d1 = hms(0, 0, 0);
    d2 = hms(3, 9, 0);
    d3 = hms(0, 59, 59);
    d4 = hms(3,10,10);
    d5 = hms(2,5,1);
    d6 = hms(0,0,23);
    d7 = hms(12,0,2);
    d8 = hms(12,0,0);
    d9 = hms(0,0,2);
    d10 = hms(234,0,0);
    d11 = hms(0,234,0);
    d12 = hms(0,240,0);
    d13 = hms(0,0,234);
    d14 = hms(0,0,240);
    d15 = hms(0,0,7501);

  }

  @Test
  public void testSec_T() {
    assertEquals("105",
                  sec(105).format("%t"));
    assertEquals("0 seconds",
            sec(0).format("%t seconds"));
    assertEquals("75760 seconds",
            sec(75760).format("%t seconds"));
    assertEquals("23",
            sec(23).format("%t"));
    assertEquals("23",
            sec(23).format("%t"));
  }

  @Test
  public void testHms_T() {
    initialize();
    assertEquals("0 seconds",
            d1.format("%t seconds"));
    assertEquals("11410 seconds",
            d4.format("%t seconds"));
    assertEquals("7501 seconds",
            d5.format("%t seconds"));
    assertEquals("23",
            d6.format("%t"));
    assertEquals("23",
            d6.format("%t "));
  }

  @Test
  public void testSec_Hours() {
    assertEquals("12 pm is my favorite time of the day.",
            sec(45458).format("%h pm is my favorite time of the day."));
    assertEquals("there are 100 minutes left of class.",
            sec(360000).format("there are %h minutes left of class."));
    assertEquals("212",
            sec(765780).format("%h"));
    assertEquals("2",
            sec(7866).format("%h"));
    assertEquals("0",
            sec(0).format("%h"));
  }

  @Test
  public void testHms_Hours() {
    initialize();
    assertEquals("3 pm is my favorite time of the day.",
            d4.format("%h pm is my favorite time of the day."));
    assertEquals("12:0:2",
            d7.format("%h:%m:%s"));
    assertEquals("3:9:0",
            d2.format("%h:%m:%s"));
    assertEquals("there are 0 minutes left of class.",
            d1.format("there are %h minutes left of class."));
  }

  @Test
  public void testSec_Minutes() {
    assertEquals("15 pm is my favorite time of the day.",
            sec(4548).format("%m pm is my favorite time of the day."));
    assertEquals("45",
            sec(67543).format("%m"));
    assertEquals("0",
            sec(360000).format("%m"));
    assertEquals("there are 0 minutes left of class.",
            sec(360000).format("there are %m minutes left of class."));
    assertEquals("0",
            sec(0).format("%m"));
  }

  @Test
  public void testHms_Minutes() {
    initialize();
    assertEquals("0 pm is my favorite time of the day.",
            d7.format("%m pm is my favorite time of the day."));
    assertEquals("10",
            d4.format("%m"));
    assertEquals("0",
            d7.format("%m"));
    assertEquals("there are 0 minutes left of class.",
            d7.format("there are %m minutes left of class."));
  }

  @Test
  public void testSec_Seconds() {
    assertEquals("48 pm is my favorite time of the day.",
            sec(4548).format("%s pm is my favorite time of the day."));
    assertEquals("3",
            sec(3).format("%s"));
    assertEquals("49",
            sec(454533829).format("%s"));
    assertEquals("A lifetime has 38 seconds.",
            sec(45458).format("A lifetime has %s seconds."));
    assertEquals("1:0:0",
            sec(3600).format("%h:%m:%s"));
  }

  @Test
  public void testHms_Seconds() {
    initialize();
    assertEquals("2 pm is my favorite time of the day.",
            d7.format("%s pm is my favorite time of the day."));
    assertEquals("0",
            d14.format("%s"));
    assertEquals("10",
            d4.format("%s"));
    assertEquals("lalala 10 lalalala.",
            d4.format("lalala %s lalalala."));
  }

  @Test
  public void testSec_PaddedHours() {
    assertEquals("12 pm is my favorite time of the day.",
            sec(45458).format("%H pm is my favorite time of the day."));
    assertEquals("00",
            sec(4).format("%H"));
    assertEquals("00",
            sec(0).format("%H"));
    assertEquals("00",
            sec(1).format("%H"));
    assertEquals("805",
            sec(2898800).format("%H"));
    assertEquals("80",
            sec(289880).format("%H"));
    assertEquals("there are 02 minutes left of class.",
            sec(8787).format("there are %H minutes left of class."));
  }

  @Test
  public void testHms_PaddedHours() {
    initialize();
    assertEquals("12 pm is my favorite time of the day.",
            d7.format("%H pm is my favorite time of the day."));
    assertEquals("12:0:2",
            d7.format("%H:%m:%s"));
    assertEquals("12:0:0",
            d8.format("%H:%m:%s"));
    assertEquals("there are 02 minutes left of class.",
            d15.format("there are %H minutes left of class."));
  }

  @Test
  public void testSec_PaddedMinutes() {
    assertEquals("15 pm is my favorite time of the day.",
            sec(4548).format("%M pm is my favorite time of the day."));
    assertEquals("lalalal 15 lalala.",
            sec(4548).format("lalalal %M lalala."));
    assertEquals("45",
            sec(67543).format("%M"));
    assertEquals("00",
            sec(360000).format("%M"));
  }

  @Test
  public void testHms_PaddedMinutes() {
    initialize();
    assertEquals("00 pm is my favorite time of the day.",
            d7.format("%M pm is my favorite time of the day."));
    assertEquals("00",
            d7.format("%M"));
    assertEquals("10",
            d4.format("%M"));
    assertEquals("there are 00 minutes left.",
            d7.format("there are %M minutes left."));
    assertEquals("lalala 10 lalalaa.",
            d4.format("lalala %M lalalaa."));
  }

  @Test
  public void testSec_PaddedSeconds() {
    assertEquals("48 pm is my favorite time of the day.",
            sec(4548).format("%S pm is my favorite time of the day."));
    assertEquals("03",
            sec(3).format("%S"));
    assertEquals("38",
            sec(45458).format("%S"));
    assertEquals("A lifetime has 00 seconds.",
            sec(3600).format("A lifetime has %S seconds."));
    assertEquals("1:0:00",
            sec(3600).format("%h:%m:%S"));
  }

  @Test
  public void testHms_PaddedSeconds() {
    initialize();
    assertEquals("02 pm is my favorite time of the day.",
            d7.format("%S pm is my favorite time of the day."));
    assertEquals("00",
            d11.format("%S"));
    assertEquals("54",
            d13.format("%S"));
    assertEquals("A lifetime has 01 seconds.",
            d15.format("A lifetime has %S seconds."));
  }

  @Test
  public void testSec_AllPaddedDuration() {
    assertEquals("00:01:45",
            sec(105).format("%H:%M:%S"));
    assertEquals("105:13:19 ",
            sec(378799).format("%H:%M:%S "));
    assertEquals("00:00:00 ",
            sec(0).format("%H:%M:%S "));
  }

  @Test
  public void testHms_AllPaddedDuration() {
    initialize();
    assertEquals("00:00:23",
            d6.format("%H:%M:%S"));
    assertEquals("00:00:2",
            d9.format("%H:%M:%s"));
    assertEquals("234:00:00",
            d10.format("%H:%M:%S"));
    assertEquals("3:54:00",
            d11.format("%h:%M:%S"));
    assertEquals("04:00:00",
            d12.format("%H:%M:%S"));
    assertEquals("00:3:54",
            d13.format("%H:%m:%S"));
    assertEquals("00:04:00",
            d14.format("%H:%M:%S"));
    assertEquals("02:05:01",
            d15.format("%H:%M:%S"));
    assertEquals("00 00 00",
            d1.format("%H %M %S"));
    assertEquals("04:00 :00",
            d12.format("%H:%M :%S"));
    assertEquals("04:00 :00 ",
            d12.format("%H:%M :%S "));
    assertEquals(" 04 :00 :00 ",
            d12.format(" %H :%M :%S "));
  }

  @Test
  public void testSec_PercentSign() {
    initialize();
    assertEquals("0%", sec(0).format("%s%%"));
    assertEquals("%%t", sec(879).format("%%%%t"));
    assertEquals("%t", sec(879).format("%%t"));
    assertEquals("%%",
            sec(3456).format("%%%%"));
    assertEquals("%%t of time taken",
            sec(999).format("%%%%t of time taken"));
    assertEquals("%t seconds",
            sec(231).format("%%t seconds"));
    assertEquals("0:01:%S",
            sec(111).format("%h:%M:%%S"));
    assertEquals("%3 hours, 25, and 22 seconds",
            sec(12322).format("%%%h hours, %m, and %s seconds"));
    assertEquals("%%h hours, 18 minutes, and 32 seconds",
            sec(1112).format("%%%%h hours, %m minutes, and %s seconds"));
    assertEquals("I'm 100% sure that it took 3 hours, 5 minutes, and 41 seconds",
            sec(11141).format("I'm 100%% sure that it took %h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void testHms_PercentSign() {
    initialize();
    assertEquals("0%", d1.format("%s%%"));
    assertEquals("%%t", d6.format("%%%%t"));
    assertEquals("%t", d6.format("%%t"));
    assertEquals("%%t of time taken",
            d1.format("%%%%t of time taken"));
    assertEquals("%t seconds",
            d7.format("%%t seconds"));
    assertEquals("2:05:%S",
            d5.format("%h:%M:%%S"));
    assertEquals("%0 hours, 59, and 59 seconds",
            d3.format("%%%h hours, %m, and %s seconds"));
    assertEquals("%%h hours, 59 minutes, and 59 seconds",
            d3.format("%%%%h hours, %m minutes, and %s seconds"));
    assertEquals("I'm 100% sure that it took 3 hours, 9 minutes, and 0 seconds",
            d2.format("I'm 100%% sure that it took %h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void testSec_DoublePercentSign() {
    assertEquals("1:%M:00", sec(3600).format("%h:%%M:%S"));
    assertEquals("%h:00:00", sec(3600).format("%%h:%M:%S"));
    assertEquals("1:00:%S", sec(3600).format("%h:%M:%%S"));
    assertEquals("1:00:%S ", sec(3600).format("%h:%M:%%S "));

  }

  @Test
  public void testHms_DoublePercentSign() {
    initialize();
    assertEquals("0:%M:00", d1.format("%h:%%M:%S"));
    assertEquals("%h:00:00", d1.format("%%h:%M:%S"));
    assertEquals("0:00:%S", d1.format("%h:%M:%%S"));
    assertEquals("0:00:%S ", d1.format("%h:%M:%%S "));
  }

  @Test
  public void testSec_Overlapping() {
    assertEquals("%h", sec(3600).format("%%h"));
    assertEquals("%H", sec(3600).format("%%H"));
    assertEquals("%m", sec(3600).format("%%m"));
    assertEquals("%M", sec(3600).format("%%M"));
    assertEquals("%s", sec(3600).format("%%s"));
    assertEquals("%S", sec(3600).format("%%S"));
    assertEquals("%h, %H, %m, %M, %s, %S, ", sec(3600).format("%%h, %%H, %%m, %%M, %%s, %%S, "));
    assertEquals("%h %H %m %M %s %S", sec(3600).format("%%h %%H %%m %%M %%s %%S"));
    assertEquals("%h%H%m%M%s%S", sec(3600).format("%%h%%H%%m%%M%%s%%S"));
  }

  @Test
  public void testHms_Overlapping() {
    initialize();
    assertEquals("%h", d1.format("%%h"));
    assertEquals("%H", d1.format("%%H"));
    assertEquals("%m", d1.format("%%m"));
    assertEquals("%M", d1.format("%%M"));
    assertEquals("%s", d1.format("%%s"));
    assertEquals("%S", d1.format("%%S"));
    assertEquals("%h, %H, %m, %M, %s, %S, ", d1.format("%%h, %%H, %%m, %%M, %%s, %%S, "));
    assertEquals("%h %H %m %M %s %S", d1.format("%%h %%H %%m %%M %%s %%S"));
    assertEquals("%h%H%m%M%s%S", d1.format("%%h%%H%%m%%M%%s%%S"));
  }

  @Test
  public void testSec_Spaces() {
    initialize();
    assertEquals(" ", sec(0).format(" "));
    assertEquals("   0:0:0   ",
            sec(0).format("   %h:%m:%s   "));
  }

  @Test
  public void testHms_Spaces() {
    initialize();
    assertEquals(" ", d1.format(" "));
    assertEquals("   0:0:0   ",
            d1.format("   %h:%m:%s   "));
  }

  @Test
  public void testSec_OnlyUniterpreted() {
    assertEquals("Java Jock", sec(3123).format("Java Jock"));
  }

  @Test
  public void testHms_OnlyUniterpreted() {
    initialize();
    assertEquals("Java Jock", d1.format("Java Jock"));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSec_malformedFormat1() {
    sec(378799).format("% t");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSec_malformedFormat2() {
    sec(378799).format("   %h:%m:%s   %");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSec_malformedFormat3() {
    sec(378799).format("%h:%m:%s %f");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSec_malformedFormat4() {
    sec(378799).format("%f %h:%m:%s");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSec_malformedFormat5() {
    sec(378799).format("%%%");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSec_malformedFormat6() {
    sec(378799).format("%%%%t");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSec_malformedFormat7() {
    sec(378799).format("% %%%t");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSec_malformedFormat8() {
    sec(378799).format("%r");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSec_malformedFormat9() {
    sec(378799).format("%");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSec_malformedFormat10() {
    sec(45678900).format("%j:%m:%");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testHms_malformedFormat2() {
    initialize();
    d1.format("% t");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testHms_malformedFormat3() {
    initialize();
    d1.format("%");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testHms_malformedFormat4() {
    initialize();
    d1.format("   %h:%m:%s   %");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testHms_malformedFormat5() {
    initialize();
    d1.format("%h:%m:%s %f");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testHms_malformedFormat6() {
    initialize();
    d1.format("%f %h:%m:%s");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testHms_malformedFormat7() {
    initialize();
    d1.format("%%%");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testHms_malformedFormat8() {
    initialize();
    d6.format("%%%%t");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testHms_malformedFormat9() {
    initialize();
    d6.format("% %%%t");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testHms_malformedFormat10() {
    initialize();
    d6.format("%r");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testHms_malformedFormat11() {
    initialize();
    d1.format("%j:%m:%");
  }

  @Test
  public void testHms_EmptyString() {
    initialize();
    assertEquals("", d1.format(""));
  }

  @Test
  public void testSec_EmptyString() {
    assertEquals("", sec(0).format(""));
  }

  @Test (expected = NullPointerException.class)
  public void testHms_Null() {
    initialize();
    d1.format(null);
  }

  @Test (expected = NullPointerException.class)
  public void testSec_Null() {
    sec(0).format(null);
  }

  */
/*
    Leave this section alone: It contains two abstract methods to
    create Durations, and concrete implementations of this testing class
    will supply particular implementations of Duration to be used within 
    your tests.
   *//*

  */
/**
   * Constructs an instance of the class under test representing the duration
   * given in hours, minutes, and seconds.
   *
   * @param hours the hours in the duration
   * @param minutes the minutes in the duration
   * @param seconds the seconds in the duration
   * @return an instance of the class under test
   *//*

  protected abstract Duration hms(int hours, int minutes, int seconds);

  */
/**
   * Constructs an instance of the class under test representing the duration
   * given in seconds.
   *
   * @param inSeconds the total seconds in the duration
   * @return an instance of the class under test
   *//*

  protected abstract Duration sec(long inSeconds);

  public static final class HmsDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new HmsDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new HmsDuration(inSeconds);
    }
  }

  public static final class CompactDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new CompactDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new CompactDuration(inSeconds);
    }
  }
}
*/

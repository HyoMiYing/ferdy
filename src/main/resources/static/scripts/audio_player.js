    // UI elements
    const previousChapterButton = document.getElementById("previous-chapter");
    const nextChapterButton = document.getElementById("next-chapter");


    const audioPlayer = document.getElementById("audio-player");

    const mainInfoDiv = document.getElementById("main-info");

    const introFileSrc = audioPlayer.src;

    const currentlyPlaying = document.getElementById("currently-playing");
    const trackListened = document.getElementById("track-listened");
    const trackLeft = document.getElementById("track-left");

    const playPauseButton = document.getElementById("play-pause");
    const playPauseButtonContainer = document.getElementById("play-pause-container");

    // Chapter menu elements

    const chaptersListButton = document.getElementById("chapter-list");

    const chaptersMenu = document.getElementById("chapters-menu");
    const closeChaptersMenu = document.getElementById("close-chapters-menu");    // openBookmarksMenu.addEventListener("click", (e) => {
        //     bookmarksMenu.style.display = "block";
        //     chaptersMenu.style.display = "none";
        // });
    
        // openChaptersMenu.addEventListener("click", (e) => {
        //     bookmarksMenu.style.display = "none";
        //     chaptersMenu.style.display = "block";
        // });
    
        // closeBookmarksMenu.addEventListener("click", (e) => {
        //     bookmarksMenu.style.display = "none";
        // });

    //

    
    chaptersListButton.addEventListener("click", (e) => {
        chaptersMenu.style.display = "block";
    });
    
    closeChaptersMenu.addEventListener("click", (e) => {
        chaptersMenu.style.display = "none";
    });
    
    const timeLeftWholeBook = document.getElementById("time-left-whole-book");

    const progressBar = document.getElementById("progress-slider");

    progressBar.addEventListener("change", (e) => {
        const currentChapter = audioPlayer.src.split("=")[1];
        const currentChapterLength = chapterSourcesAndLength[currentChapter];
        audioPlayer.currentTime = progressBar.value;
    });

    progressBar.addEventListener("input", (e) => {
        trackListened.textContent = sToTime(progressBar.value);
        trackLeft.textContent = sToTime(audioPlayer.duration - progressBar.value);
    });


    const chapterSourcesAndLength = {
        // in seconds
        "uvod.mp3": 21,
        "prvo_poglavje.mp3": 2917,
        "drugo_poglavje.mp3": 3996,
        "tretje_poglavje.mp3": 3020,
        "cetrto_poglavje.mp3": 3017,
        "peto_poglavje.mp3": 2318,
        "sesto_poglavje.mp3": 2320,
        "sedmo_poglavje.mp3": 1719,
        "osmo_poglavje.mp3": 2262,
        "deveto_poglavje.mp3": 3042,
        "deseto_poglavje.mp3": 3342,
        "enajsto_poglavje.mp3": 596,
        "dvanajsto_poglavje.mp3": 356,
        "trinajsto_poglavje.mp3": 4835,
        "stirinajsto_poglavje.mp3": 7010,
    }

    // const fakeLinks = document.getElementsByClassName("fake-link");

    // for (let link of fakeLinks) {
    //     link.preventDefault();
    // }



    playPauseButtonContainer.addEventListener("click", (e) => {
        if (playPauseButton.classList.contains("icono-play")) {
            audioPlayer.play();
            playPauseButton.classList.remove("icono-play");
            playPauseButton.classList.add("icono-pause");
        } else if (playPauseButton.classList.contains("icono-pause")) {
            audioPlayer.pause();
            playPauseButton.classList.remove("icono-pause");
            playPauseButton.classList.add("icono-play");
        }
    });

    previousChapterButton.addEventListener("click", (e) => {
        const currentChapterIndex = chapters.indexOf(audioPlayer.src.split("=")[1]);
        if (currentChapterIndex == 0) {
            return;
        }
        chaptersMenu.style.display = "none";
        audioPlayer.src = changeAudioPlayerSrc(chapters[currentChapterIndex-1], introFileSrc);
        markChapterAsSelected(chapters[currentChapterIndex-1]);
        redefineRangeInputValue();
        playAudio();
        audioPlayer.play();
    });

    nextChapterButton.addEventListener("click", (e) => {
        const currentChapterIndex = chapters.indexOf(audioPlayer.src.split("=")[1]);
        if (currentChapterIndex == 14) {
            return;
        }
        chaptersMenu.style.display = "none";
        audioPlayer.src = changeAudioPlayerSrc(chapters[currentChapterIndex+1], introFileSrc);
        markChapterAsSelected(chapters[currentChapterIndex+1]);
        redefineRangeInputValue();
        playAudio();
        audioPlayer.play();
    });

    function playAudio () {
        playPauseButton.classList.remove("icono-play");
        playPauseButton.classList.remove("icono-pause");
        playPauseButton.classList.add("icono-pause");
    }

    const chapters = [
        "uvod.mp3",
        "prvo_poglavje.mp3",
        "drugo_poglavje.mp3",
        "tretje_poglavje.mp3",
        "cetrto_poglavje.mp3",
        "peto_poglavje.mp3",
        "sesto_poglavje.mp3",
        "sedmo_poglavje.mp3",
        "osmo_poglavje.mp3",
        "deveto_poglavje.mp3",
        "deseto_poglavje.mp3",
        "enajsto_poglavje.mp3",
        "dvanajsto_poglavje.mp3",
        "trinajsto_poglavje.mp3",
        "stirinajsto_poglavje.mp3",
    ];

    const chapterSourcesAndNamesLong = {
        "uvod.mp3": "UVOD",
        "prvo_poglavje.mp3": "1. POGLAVJE: UGRABITEV",
        "drugo_poglavje.mp3": "2. POGLAVJE: V JETNIŠTVU IN NADALJNEM MANJŠANJU",
        "tretje_poglavje.mp3": "3. POGLAVJE: V MIŠNICI IN NADALJNEM MEČKANJU",
        "cetrto_poglavje.mp3": "4. POGLAVJE: PREDGOVOR K FILIDORJU S PODLAGO V OTROKU",
        "peto_poglavje.mp3": "5. POGLAVJE: FILIDOR, S PODLAGO V OTROKU",
        "sesto_poglavje.mp3": "6. POGLAVJE: ZAPELJEVANJE, SILJENJE V MLADOST SE NADALJUJE",
        "sedmo_poglavje.mp3": "7. POGLAVJE: LJUBEZEN",
        "osmo_poglavje.mp3": "8. POGLAVJE: KOMPOT",
        "deveto_poglavje.mp3": "9. POGLAVJE: ZALEZOVANJE, TIPANJE V SODOBNOST SE NADALJUJE",
        "deseto_poglavje.mp3": "10. POGLAVJE: ORGIJA STEGEN IN ZNOVA V MIŠNICI",
        "enajsto_poglavje.mp3": "11. POGLAVJE: PREDGOVOR K FILIBERTU, PODLOŽENEMU Z OTROKOM",
        "dvanajsto_poglavje.mp3": "12. POGLAVJE: FILIBERT, PODLOŽEN Z OTROKOM",
        "trinajsto_poglavje.mp3": "13. POGLAVJE: HLAPČIČ ALI NOVE NEVARNOSTI",
        "stirinajsto_poglavje.mp3": "14. POGLAVJE: ORGIJA FRISOV IN ZNOVA V MIŠNICI",
    }

    const chapterSourcesAndNames = {
        "https://samplelib.com/lib/preview/mp3/sample-15s.mp3": "UVOD",
        "https://freetestdata.com/wp-content/uploads/2021/09/Free_Test_Data_5MB_MP3.mp3": "1. POGLAVJE",
        "https://freetestdata.com/wp-content/uploads/2021/09/Free_Test_Data_5MB_MP3.mp3": "2. POGLAVJE",
        "https://file-examples.com/storage/fef1706276640fa2f99a5a4/2017/11/file_example_MP3_2MG.mp3": "3. POGLAVJE",
        "https://file-examples.com/storage/fef1706276640fa2f99a5a4/2017/11/file_example_MP3_2MG.mp3": "4. POGLAVJE",
        "media/peto_poglavje.mp3": "5. POGLAVJE",
        "media/sesto_poglavje.mp3": "6. POGLAVJE",
        "media/sedmo_poglavje.mp3": "7. POGLAVJE",
        "media/osmo_poglavje.mp3": "8. POGLAVJE",
        "media/deveto_poglavje.mp3": "9. POGLAVJE",
        "media/deseto_poglavje.mp3": "10. POGLAVJE",
        "media/enajsto_poglavje.mp3": "11. POGLAVJE",
        "media/dvanajsto_poglavje.mp3": "12. POGLAVJE",
        "media/trinajsto_poglavje.mp3": "13. POGLAVJE",
        "media/stirinajsto_poglavje.mp3": "14. POGLAVJE",
    }


    const back10Sec = document.getElementById("back-10-sec");
    const forward10Sec = document.getElementById("forward-10-sec");

    const x05 = document.getElementById("x0.5");
    const x075 = document.getElementById("x0.75");
    const x1 = document.getElementById("x1.0");
    const x125 = document.getElementById("x1.25");
    const x15 = document.getElementById("x1.5");
    const x175 = document.getElementById("x1.75");
    const x2 = document.getElementById("x2.0");

    const intro = document.getElementById("intro");
    const chapter1 = document.getElementById("chapter1");
    const chapter2 = document.getElementById("chapter2");
    const chapter3 = document.getElementById("chapter3");
    const chapter4 = document.getElementById("chapter4");
    const chapter5 = document.getElementById("chapter5");
    const chapter6 = document.getElementById("chapter6");
    const chapter7 = document.getElementById("chapter7");
    const chapter8 = document.getElementById("chapter8");
    const chapter9 = document.getElementById("chapter9");
    const chapter10 = document.getElementById("chapter10");
    const chapter11 = document.getElementById("chapter11");
    const chapter12 = document.getElementById("chapter12");
    const chapter13 = document.getElementById("chapter13");
    const chapter14 = document.getElementById("chapter14");

    function getTimeFromStartForChapter(chapter) {
        const indexOfChapter = chapters.indexOf(chapter);
        let seconds = 0;
        for (let i = 0; i < indexOfChapter; i++) {
            seconds += chapterSourcesAndLength[chapters[i]];
        }
        return seconds;
    }

    document.addEventListener("DOMContentLoaded", (e) => {
        const currentTime = audioPlayer.currentTime.toFixed(0);

        if (localStorage.getItem("playhead-position")) {
            let chapterFilename = localStorage.getItem("chapter-source");
            audioPlayer.src = changeAudioPlayerSrc(chapterFilename, introFileSrc);
            markChapterAsSelected(chapterFilename.replace(".mp3", ""));
            audioPlayer.currentTime = localStorage.getItem("playhead-position") - 5;
            redefineRangeInputValue();
        } else {
            let chapterFilename = "uvod.mp3";
            audioPlayer.src = changeAudioPlayerSrc(chapterFilename, introFileSrc);
            markChapterAsSelected(chapterFilename.replace(".mp3", ""));
            audioPlayer.currentTime = 0.1;
            redefineRangeInputValue();
        }
        trackListened.textContent = sToTime(currentTime);
        progressBar.value = currentTime;
        progressBar.style.setProperty("--max", chapterSourcesAndLength[audioPlayer.src.split("=")[1]]);
        progressBar.style.setProperty("--value", currentTime);
        trackLeft.textContent = sToTime(audioPlayer.duration - currentTime);
        currentlyPlaying.textContent = chapterSourcesAndNamesLong[audioPlayer.src.split("=")[1]];
        timeLeftWholeBook.textContent = calculateTimeUntillEnd(audioPlayer.src.split("=")[1], audioPlayer.duration - currentTime);
    });


    // Play next chapter if current one ended

    audioPlayer.addEventListener("ended", (e) => {
        let currentSrc = audioPlayer.src.split("=")[1];
        let arrayPosition = chapters.indexOf(currentSrc);
        if (arrayPosition == 14 || arrayPosition == -1) {
            return;
        }
        audioPlayer.src = changeAudioPlayerSrc(chapters[arrayPosition + 1], introFileSrc);
        audioPlayer.play();
    });


    // Play speed

    x05.addEventListener("click", (e) => {
        audioPlayer.playbackRate = 0.5;
    });

    x075.addEventListener("click", (e) => {
        audioPlayer.playbackRate = 0.75;
    });

    x1.addEventListener("click", (e) => {
        audioPlayer.playbackRate = 1.0;
    });

    x125.addEventListener("click", (e) => {
        audioPlayer.playbackRate = 1.25;
    });

    x15.addEventListener("click", (e) => {
        audioPlayer.playbackRate = 1.5;
    });

    x175.addEventListener("click", (e) => {
        audioPlayer.playbackRate = 1.75;
    });

    x2.addEventListener("click", (e) => {
        audioPlayer.playbackRate = 2.0;
    });

    // Rewind/fast forward

    back10Sec.addEventListener("click", (e) => {
        audioPlayer.currentTime -= 10;
    });

    forward10Sec.addEventListener("click", (e) => {
        audioPlayer.currentTime += 10;
    });

    // Store playhead position && update time listened and time left text && update currently playing info

    audioPlayer.addEventListener("timeupdate", (e) => {
        const currentTime = audioPlayer.currentTime.toFixed(0);
        localStorage.setItem("playhead-position", currentTime);
        localStorage.setItem("chapter-source", audioPlayer.src.split("=")[1]);
        trackListened.textContent = sToTime(currentTime);
        progressBar.value = currentTime;
        progressBar.style.setProperty("--max", chapterSourcesAndLength[audioPlayer.src.split("=")[1]]);
        progressBar.style.setProperty("--value", currentTime);
        trackLeft.textContent = sToTime(audioPlayer.duration - currentTime);
        currentlyPlaying.textContent = chapterSourcesAndNamesLong[audioPlayer.src.split("=")[1]];
        timeLeftWholeBook.textContent = calculateTimeUntillEnd(audioPlayer.src.split("=")[1], audioPlayer.duration - currentTime);
    });

    function calculateTimeUntillEnd(chapterSource, trackLeft) {
        let secondsUntillEnd = trackLeft;
        const indexOfCurrentChapter = chapters.indexOf(chapterSource);
        for (let step = indexOfCurrentChapter+1; step <= chapters.length-1; step++) {
            secondsUntillEnd += chapterSourcesAndLength[chapters[step]];
        }
        const minutesAndSecondsUntillEnd = sToHourAndMin(secondsUntillEnd);
        return minutesAndSecondsUntillEnd;
    }

    // Helper methods seconds to hour:min:sec

    function sToTime(t) {
        return padZero(parseInt((t / (60 * 60)) % 24)) + ":" +
            padZero(parseInt((t / (60)) % 60)) + ":" +
            padZero(parseInt((t) % 60));
    }

    function sToHourAndMin(t) {
        return parseInt((t / (60 * 60)) % 24) + "h " +
            parseInt((t / (60)) % 60) + "m";
    }

    function padZero(v) {
        return (v < 10) ? "0" + v : v;
    }

    // Helper methods to switch chapters in the player
    function changeAudioPlayerSrc(chapterSrc, baseSrc) {
        const newFileSrc = baseSrc.replace(/=(.*)/, "=" + chapterSrc);
        return newFileSrc;
    }

    function markChapterAsSelected(chapter) {
        const chapterElements = document.getElementsByClassName("chapter");
        for (let i = 0; i < chapterElements.length; i++) {
            chapterElements[i].classList.remove("chapter-now-playing");
            if (chapterElements[i].classList.contains(chapter)) {
                chapterElements[i].classList.add("chapter-now-playing");
            }
        }
    }

    //

    function redefineRangeInputValue () {
        progressBar.setAttribute("max", chapterSourcesAndLength[audioPlayer.src.split("=")[1]]);
    }

    // Chapters

    function switchToChapter(chapterFilename) {
        chaptersMenu.style.display = "none";
        audioPlayer.src = changeAudioPlayerSrc(chapterFilename, introFileSrc);
        markChapterAsSelected(chapterFilename.replace(".mp3", ""));
        redefineRangeInputValue();
        playAudio();
        audioPlayer.play();
    }

    intro.addEventListener("click", (e) => {
        switchToChapter("uvod.mp3");
    });

    chapter1.addEventListener("click", (e) => {
        switchToChapter("prvo_poglavje.mp3");
    });

    chapter2.addEventListener("click", (e) => {
        switchToChapter("drugo_poglavje.mp3");
    });

    chapter3.addEventListener("click", (e) => {
        switchToChapter("tretje_poglavje.mp3");
    });

    chapter4.addEventListener("click", (e) => {
        switchToChapter("cetrto_poglavje.mp3");
    });

    chapter5.addEventListener("click", (e) => {
        switchToChapter("peto_poglavje.mp3");
    });

    chapter6.addEventListener("click", (e) => {
        switchToChapter("sesto_poglavje.mp3");
    });

    chapter7.addEventListener("click", (e) => {
        switchToChapter("sedmo_poglavje.mp3");
    });

    chapter8.addEventListener("click", (e) => {
        switchToChapter("osmo_poglavje.mp3");
    });

    chapter9.addEventListener("click", (e) => {
        switchToChapter("deveto_poglavje.mp3");
    });

    chapter10.addEventListener("click", (e) => {
        switchToChapter("deseto_poglavje.mp3");
    });

    chapter11.addEventListener("click", (e) => {
        switchToChapter("enajsto_poglavje.mp3");
    });

    chapter12.addEventListener("click", (e) => {
        switchToChapter("dvanajsto_poglavje.mp3");
    });

    chapter13.addEventListener("click", (e) => {
        switchToChapter("trinajsto_poglavje.mp3");
    });

    chapter14.addEventListener("click", (e) => {
        switchToChapter("stirinajsto_poglavje.mp3");
    });
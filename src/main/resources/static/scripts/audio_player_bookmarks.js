const openBookmarksMenu = document.getElementById("open-bookmarks-menu");

// Bookmarks menu elements

const bookmarksMenu = document.getElementById("bookmarks-menu");
const openChaptersMenu = document.getElementById("open-chapters-menu");
const closeBookmarksMenu = document.getElementById("close-bookmarks-menu");
const bookmarkTimestamps = document.getElementsByClassName("bookmark-timestamp");

//

const addBookmark = document.getElementById("add-bookmark");
const bookmarks = document.getElementById("bookmarks");

// Add bookmark
    const addBookmarkForm = document.getElementById("add-bookmark-form");

    addBookmark.addEventListener("click", () => { 
        audioPlayer.pause();
        const bookmarkNote = window.prompt("Opomba za zaznamek:");
        let chapterField = document.getElementById("chapter-field");
        chapterField.value = audioPlayer.currentSrc.replace(/(.*)=/, "");
        let timestampField = document.getElementById("timestamp-field");
        timestampField.value = audioPlayer.currentTime;
        let noteField = document.getElementById("note-field");
        noteField.value = bookmarkNote;
        addBookmarkForm.submit(); 
    });

    // Play bookmark OR delete a bookmark
    bookmarks.addEventListener("click", (e) => {
        if (e.target.classList.contains("bookmark-list-item") || e.target.parentElement.classList.contains("bookmark-list-item")) {
            if (e.target.classList.contains("delete-bookmark")) {
                e.target.parentElement.remove();
                return;
            }
            if (e.target.getAttribute("data-bookmark-chapter") == null) {
                audioPlayer.src = window.location.href.replace(/prijavljeni(.*)/, "prijavljeni/ferdydurke_stream?chapter=" + e.target.parentElement.getAttribute("data-bookmark-chapter"));
                audioPlayer.currentTime = parseInt(e.target.parentElement.getAttribute("data-bookmark-timestamp"));
                audioPlayer.play();
                playAudio();
                redefineRangeInputValue();
                bookmarksMenu.style.display = "none";
                return
            } else {
                audioPlayer.src = window.location.href.replace(/prijavljeni(.*)/, "prijavljeni/ferdydurke_stream?chapter=" + e.target.getAttribute("data-bookmark-chapter"));
                audioPlayer.currentTime = parseInt(e.target.getAttribute("data-bookmark-timestamp"));
                audioPlayer.play();
                playAudio();
                redefineRangeInputValue();
                bookmarksMenu.style.display = "none";
            }
        }
    });
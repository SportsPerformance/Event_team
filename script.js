function showSection(sectionId) {
    let sections = document.querySelectorAll('.section');
    sections.forEach(section => section.classList.remove('active'));
    document.getElementById(sectionId).classList.add('active');
}

function printPage() {
    window.print();
}

function filterEvents() {
    let search = document.getElementById('search').value.toLowerCase();
    let rows = document.querySelectorAll('#eventsTable tbody tr');

    rows.forEach(row => {
        let eventTitle = row.cells[1].innerText.toLowerCase();
        let meetName = row.cells[2].innerText.toLowerCase();

        if (eventTitle.includes(search) || meetName.includes(search)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

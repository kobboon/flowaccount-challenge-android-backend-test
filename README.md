## Android
ทำแอพพลิเคชั่นสำหรับแสดงผลการค้นหาจาก Github Search API
1. ช่องพิมพ์สำหรับค้นหา
2. หน้า List สำหรับแสดงผลการค้นหา
3. แต่ละ item แสดงภาพ avatar, name และ description
4. แสดงผลทีละ 20 รายการ
5. เมื่อเลื่อนลงมาถึงรายการสุดท้าย จะทำการโหลดรายการต่อไปทันที (infinite scroll)
ตัวอย่างการเรียกใช้ API https://api.github.com/search/repositories?q=android&per_page=20

## ASP.net 
ใช้ ASP.NET CORE ทำ JSON API response format แบบ RESTful
1. เชื่อมฐานข้อมูล MQQSL 
2. รองรับ pagination ดึงข้อมูลสูงสุดได้ครั้งละ 20 รายการ
3. ทำสำหรับสอง table ทำ datahandler เป็น generic

### push code for review and submit test

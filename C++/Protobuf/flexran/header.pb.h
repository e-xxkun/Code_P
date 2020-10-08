// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: header.proto

#ifndef GOOGLE_PROTOBUF_INCLUDED_header_2eproto
#define GOOGLE_PROTOBUF_INCLUDED_header_2eproto

#include <limits>
#include <string>

#include <google/protobuf/port_def.inc>
#if PROTOBUF_VERSION < 3013000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers. Please update
#error your headers.
#endif
#if 3013000 < PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers. Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/port_undef.inc>
#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/arena.h>
#include <google/protobuf/arenastring.h>
#include <google/protobuf/generated_message_table_driven.h>
#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/inlined_string_field.h>
#include <google/protobuf/metadata_lite.h>
#include <google/protobuf/generated_message_reflection.h>
#include <google/protobuf/message.h>
#include <google/protobuf/repeated_field.h>  // IWYU pragma: export
#include <google/protobuf/extension_set.h>  // IWYU pragma: export
#include <google/protobuf/generated_enum_reflection.h>
#include <google/protobuf/unknown_field_set.h>
// @@protoc_insertion_point(includes)
#include <google/protobuf/port_def.inc>
#define PROTOBUF_INTERNAL_EXPORT_header_2eproto
PROTOBUF_NAMESPACE_OPEN
namespace internal {
class AnyMetadata;
}  // namespace internal
PROTOBUF_NAMESPACE_CLOSE

// Internal implementation detail -- do not use these members.
struct TableStruct_header_2eproto {
  static const ::PROTOBUF_NAMESPACE_ID::internal::ParseTableField entries[]
    PROTOBUF_SECTION_VARIABLE(protodesc_cold);
  static const ::PROTOBUF_NAMESPACE_ID::internal::AuxiliaryParseTableField aux[]
    PROTOBUF_SECTION_VARIABLE(protodesc_cold);
  static const ::PROTOBUF_NAMESPACE_ID::internal::ParseTable schema[1]
    PROTOBUF_SECTION_VARIABLE(protodesc_cold);
  static const ::PROTOBUF_NAMESPACE_ID::internal::FieldMetadata field_metadata[];
  static const ::PROTOBUF_NAMESPACE_ID::internal::SerializationTable serialization_table[];
  static const ::PROTOBUF_NAMESPACE_ID::uint32 offsets[];
};
extern const ::PROTOBUF_NAMESPACE_ID::internal::DescriptorTable descriptor_table_header_2eproto;
namespace protocol {
class flex_header;
class flex_headerDefaultTypeInternal;
extern flex_headerDefaultTypeInternal _flex_header_default_instance_;
}  // namespace protocol
PROTOBUF_NAMESPACE_OPEN
template<> ::protocol::flex_header* Arena::CreateMaybeMessage<::protocol::flex_header>(Arena*);
PROTOBUF_NAMESPACE_CLOSE
namespace protocol {

enum flex_type : int {
  FLPT_HELLO = 0,
  FLPT_ECHO_REQUEST = 1,
  FLPT_ECHO_REPLY = 2,
  FLPT_DISCONNECT = 20,
  FLPT_STATS_REQUEST = 3,
  FLPT_STATS_REPLY = 4,
  FLPT_SF_TRIGGER = 5,
  FLPT_UL_SR_INFO = 6,
  FLPT_GET_ENB_CONFIG_REQUEST = 7,
  FLPT_GET_ENB_CONFIG_REPLY = 8,
  FLPT_GET_UE_CONFIG_REQUEST = 9,
  FLPT_GET_UE_CONFIG_REPLY = 10,
  FLPT_GET_LC_CONFIG_REQUEST = 11,
  FLPT_GET_LC_CONFIG_REPLY = 12,
  FLPT_DL_MAC_CONFIG = 13,
  FLPT_HO_COMMAND = 21,
  FLPT_UE_STATE_CHANGE = 14,
  FLPT_DELEGATE_CONTROL = 15,
  FLPT_RECONFIGURE_AGENT = 16,
  FLPT_RRC_TRIGGERING = 17,
  FLPT_UL_MAC_CONFIG = 18
};
bool flex_type_IsValid(int value);
constexpr flex_type flex_type_MIN = FLPT_HELLO;
constexpr flex_type flex_type_MAX = FLPT_HO_COMMAND;
constexpr int flex_type_ARRAYSIZE = flex_type_MAX + 1;

const ::PROTOBUF_NAMESPACE_ID::EnumDescriptor* flex_type_descriptor();
template<typename T>
inline const std::string& flex_type_Name(T enum_t_value) {
  static_assert(::std::is_same<T, flex_type>::value ||
    ::std::is_integral<T>::value,
    "Incorrect type passed to function flex_type_Name.");
  return ::PROTOBUF_NAMESPACE_ID::internal::NameOfEnum(
    flex_type_descriptor(), enum_t_value);
}
inline bool flex_type_Parse(
    ::PROTOBUF_NAMESPACE_ID::ConstStringParam name, flex_type* value) {
  return ::PROTOBUF_NAMESPACE_ID::internal::ParseNamedEnum<flex_type>(
    flex_type_descriptor(), name, value);
}
// ===================================================================

class flex_header PROTOBUF_FINAL :
    public ::PROTOBUF_NAMESPACE_ID::Message /* @@protoc_insertion_point(class_definition:protocol.flex_header) */ {
 public:
  inline flex_header() : flex_header(nullptr) {}
  virtual ~flex_header();

  flex_header(const flex_header& from);
  flex_header(flex_header&& from) noexcept
    : flex_header() {
    *this = ::std::move(from);
  }

  inline flex_header& operator=(const flex_header& from) {
    CopyFrom(from);
    return *this;
  }
  inline flex_header& operator=(flex_header&& from) noexcept {
    if (GetArena() == from.GetArena()) {
      if (this != &from) InternalSwap(&from);
    } else {
      CopyFrom(from);
    }
    return *this;
  }

  inline const ::PROTOBUF_NAMESPACE_ID::UnknownFieldSet& unknown_fields() const {
    return _internal_metadata_.unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(::PROTOBUF_NAMESPACE_ID::UnknownFieldSet::default_instance);
  }
  inline ::PROTOBUF_NAMESPACE_ID::UnknownFieldSet* mutable_unknown_fields() {
    return _internal_metadata_.mutable_unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>();
  }

  static const ::PROTOBUF_NAMESPACE_ID::Descriptor* descriptor() {
    return GetDescriptor();
  }
  static const ::PROTOBUF_NAMESPACE_ID::Descriptor* GetDescriptor() {
    return GetMetadataStatic().descriptor;
  }
  static const ::PROTOBUF_NAMESPACE_ID::Reflection* GetReflection() {
    return GetMetadataStatic().reflection;
  }
  static const flex_header& default_instance();

  static void InitAsDefaultInstance();  // FOR INTERNAL USE ONLY
  static inline const flex_header* internal_default_instance() {
    return reinterpret_cast<const flex_header*>(
               &_flex_header_default_instance_);
  }
  static constexpr int kIndexInFileMessages =
    0;

  friend void swap(flex_header& a, flex_header& b) {
    a.Swap(&b);
  }
  inline void Swap(flex_header* other) {
    if (other == this) return;
    if (GetArena() == other->GetArena()) {
      InternalSwap(other);
    } else {
      ::PROTOBUF_NAMESPACE_ID::internal::GenericSwap(this, other);
    }
  }
  void UnsafeArenaSwap(flex_header* other) {
    if (other == this) return;
    GOOGLE_DCHECK(GetArena() == other->GetArena());
    InternalSwap(other);
  }

  // implements Message ----------------------------------------------

  inline flex_header* New() const final {
    return CreateMaybeMessage<flex_header>(nullptr);
  }

  flex_header* New(::PROTOBUF_NAMESPACE_ID::Arena* arena) const final {
    return CreateMaybeMessage<flex_header>(arena);
  }
  void CopyFrom(const ::PROTOBUF_NAMESPACE_ID::Message& from) final;
  void MergeFrom(const ::PROTOBUF_NAMESPACE_ID::Message& from) final;
  void CopyFrom(const flex_header& from);
  void MergeFrom(const flex_header& from);
  PROTOBUF_ATTRIBUTE_REINITIALIZES void Clear() final;
  bool IsInitialized() const final;

  size_t ByteSizeLong() const final;
  const char* _InternalParse(const char* ptr, ::PROTOBUF_NAMESPACE_ID::internal::ParseContext* ctx) final;
  ::PROTOBUF_NAMESPACE_ID::uint8* _InternalSerialize(
      ::PROTOBUF_NAMESPACE_ID::uint8* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const final;
  int GetCachedSize() const final { return _cached_size_.Get(); }

  private:
  inline void SharedCtor();
  inline void SharedDtor();
  void SetCachedSize(int size) const final;
  void InternalSwap(flex_header* other);
  friend class ::PROTOBUF_NAMESPACE_ID::internal::AnyMetadata;
  static ::PROTOBUF_NAMESPACE_ID::StringPiece FullMessageName() {
    return "protocol.flex_header";
  }
  protected:
  explicit flex_header(::PROTOBUF_NAMESPACE_ID::Arena* arena);
  private:
  static void ArenaDtor(void* object);
  inline void RegisterArenaDtor(::PROTOBUF_NAMESPACE_ID::Arena* arena);
  public:

  ::PROTOBUF_NAMESPACE_ID::Metadata GetMetadata() const final;
  private:
  static ::PROTOBUF_NAMESPACE_ID::Metadata GetMetadataStatic() {
    ::PROTOBUF_NAMESPACE_ID::internal::AssignDescriptors(&::descriptor_table_header_2eproto);
    return ::descriptor_table_header_2eproto.file_level_metadata[kIndexInFileMessages];
  }

  public:

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  enum : int {
    kVersionFieldNumber = 1,
    kTypeFieldNumber = 2,
    kXidFieldNumber = 4,
  };
  // optional uint32 version = 1;
  bool has_version() const;
  private:
  bool _internal_has_version() const;
  public:
  void clear_version();
  ::PROTOBUF_NAMESPACE_ID::uint32 version() const;
  void set_version(::PROTOBUF_NAMESPACE_ID::uint32 value);
  private:
  ::PROTOBUF_NAMESPACE_ID::uint32 _internal_version() const;
  void _internal_set_version(::PROTOBUF_NAMESPACE_ID::uint32 value);
  public:

  // optional uint32 type = 2;
  bool has_type() const;
  private:
  bool _internal_has_type() const;
  public:
  void clear_type();
  ::PROTOBUF_NAMESPACE_ID::uint32 type() const;
  void set_type(::PROTOBUF_NAMESPACE_ID::uint32 value);
  private:
  ::PROTOBUF_NAMESPACE_ID::uint32 _internal_type() const;
  void _internal_set_type(::PROTOBUF_NAMESPACE_ID::uint32 value);
  public:

  // optional uint32 xid = 4;
  bool has_xid() const;
  private:
  bool _internal_has_xid() const;
  public:
  void clear_xid();
  ::PROTOBUF_NAMESPACE_ID::uint32 xid() const;
  void set_xid(::PROTOBUF_NAMESPACE_ID::uint32 value);
  private:
  ::PROTOBUF_NAMESPACE_ID::uint32 _internal_xid() const;
  void _internal_set_xid(::PROTOBUF_NAMESPACE_ID::uint32 value);
  public:

  // @@protoc_insertion_point(class_scope:protocol.flex_header)
 private:
  class _Internal;

  template <typename T> friend class ::PROTOBUF_NAMESPACE_ID::Arena::InternalHelper;
  typedef void InternalArenaConstructable_;
  typedef void DestructorSkippable_;
  ::PROTOBUF_NAMESPACE_ID::internal::HasBits<1> _has_bits_;
  mutable ::PROTOBUF_NAMESPACE_ID::internal::CachedSize _cached_size_;
  ::PROTOBUF_NAMESPACE_ID::uint32 version_;
  ::PROTOBUF_NAMESPACE_ID::uint32 type_;
  ::PROTOBUF_NAMESPACE_ID::uint32 xid_;
  friend struct ::TableStruct_header_2eproto;
};
// ===================================================================


// ===================================================================

#ifdef __GNUC__
  #pragma GCC diagnostic push
  #pragma GCC diagnostic ignored "-Wstrict-aliasing"
#endif  // __GNUC__
// flex_header

// optional uint32 version = 1;
inline bool flex_header::_internal_has_version() const {
  bool value = (_has_bits_[0] & 0x00000001u) != 0;
  return value;
}
inline bool flex_header::has_version() const {
  return _internal_has_version();
}
inline void flex_header::clear_version() {
  version_ = 0u;
  _has_bits_[0] &= ~0x00000001u;
}
inline ::PROTOBUF_NAMESPACE_ID::uint32 flex_header::_internal_version() const {
  return version_;
}
inline ::PROTOBUF_NAMESPACE_ID::uint32 flex_header::version() const {
  // @@protoc_insertion_point(field_get:protocol.flex_header.version)
  return _internal_version();
}
inline void flex_header::_internal_set_version(::PROTOBUF_NAMESPACE_ID::uint32 value) {
  _has_bits_[0] |= 0x00000001u;
  version_ = value;
}
inline void flex_header::set_version(::PROTOBUF_NAMESPACE_ID::uint32 value) {
  _internal_set_version(value);
  // @@protoc_insertion_point(field_set:protocol.flex_header.version)
}

// optional uint32 type = 2;
inline bool flex_header::_internal_has_type() const {
  bool value = (_has_bits_[0] & 0x00000002u) != 0;
  return value;
}
inline bool flex_header::has_type() const {
  return _internal_has_type();
}
inline void flex_header::clear_type() {
  type_ = 0u;
  _has_bits_[0] &= ~0x00000002u;
}
inline ::PROTOBUF_NAMESPACE_ID::uint32 flex_header::_internal_type() const {
  return type_;
}
inline ::PROTOBUF_NAMESPACE_ID::uint32 flex_header::type() const {
  // @@protoc_insertion_point(field_get:protocol.flex_header.type)
  return _internal_type();
}
inline void flex_header::_internal_set_type(::PROTOBUF_NAMESPACE_ID::uint32 value) {
  _has_bits_[0] |= 0x00000002u;
  type_ = value;
}
inline void flex_header::set_type(::PROTOBUF_NAMESPACE_ID::uint32 value) {
  _internal_set_type(value);
  // @@protoc_insertion_point(field_set:protocol.flex_header.type)
}

// optional uint32 xid = 4;
inline bool flex_header::_internal_has_xid() const {
  bool value = (_has_bits_[0] & 0x00000004u) != 0;
  return value;
}
inline bool flex_header::has_xid() const {
  return _internal_has_xid();
}
inline void flex_header::clear_xid() {
  xid_ = 0u;
  _has_bits_[0] &= ~0x00000004u;
}
inline ::PROTOBUF_NAMESPACE_ID::uint32 flex_header::_internal_xid() const {
  return xid_;
}
inline ::PROTOBUF_NAMESPACE_ID::uint32 flex_header::xid() const {
  // @@protoc_insertion_point(field_get:protocol.flex_header.xid)
  return _internal_xid();
}
inline void flex_header::_internal_set_xid(::PROTOBUF_NAMESPACE_ID::uint32 value) {
  _has_bits_[0] |= 0x00000004u;
  xid_ = value;
}
inline void flex_header::set_xid(::PROTOBUF_NAMESPACE_ID::uint32 value) {
  _internal_set_xid(value);
  // @@protoc_insertion_point(field_set:protocol.flex_header.xid)
}

#ifdef __GNUC__
  #pragma GCC diagnostic pop
#endif  // __GNUC__

// @@protoc_insertion_point(namespace_scope)

}  // namespace protocol

PROTOBUF_NAMESPACE_OPEN

template <> struct is_proto_enum< ::protocol::flex_type> : ::std::true_type {};
template <>
inline const EnumDescriptor* GetEnumDescriptor< ::protocol::flex_type>() {
  return ::protocol::flex_type_descriptor();
}

PROTOBUF_NAMESPACE_CLOSE

// @@protoc_insertion_point(global_scope)

#include <google/protobuf/port_undef.inc>
#endif  // GOOGLE_PROTOBUF_INCLUDED_GOOGLE_PROTOBUF_INCLUDED_header_2eproto